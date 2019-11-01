package com.akka.tutorials.crdt;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedAbstractActor;
import akka.cluster.ddata.*;
import akka.cluster.pubsub.DistributedPubSub;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.akka.tutorials.core.ClusterMessages;
import com.akka.tutorials.dto.CrdtMessage;

public class CrdtActor extends UntypedAbstractActor {

    ActorSystem actorSystem;
    ActorRef mediator;
    LoggingAdapter log;
    ActorRef replicator;
    private static final Key<ORMap<String, CrdtMessage>> KEY = ORMapKey.create("crdt-tutorial");
    SelfUniqueAddress node;

    public static Props props(ActorSystem actorSystem) {
        return Props.create(CrdtActor.class,
                actorSystem);
    }

    @Override
    public void preStart() {
        Replicator.Subscribe<ORMap<String, CrdtMessage>> subscribe = new Replicator.Subscribe(KEY, getSelf());
        replicator.tell(subscribe, ActorRef.noSender());
    }

    public CrdtActor(ActorSystem actorSystem) {
        this.actorSystem = actorSystem;
        this.mediator = DistributedPubSub.get(actorSystem).mediator();
        log = Logging.getLogger(actorSystem, CrdtActor.class);
        replicator = DistributedData.get(actorSystem).replicator();
        node = DistributedData.get(actorSystem).selfUniqueAddress();
    }

    @Override
    public void onReceive(Object message)  {
        if (message instanceof Replicator.Changed) {
            Replicator.Changed changed = (Replicator.Changed<ORMap<String, CrdtMessage>>) message;
            Key key = changed.key();
            if (key.equals(KEY)) {
                ORMap<String, CrdtMessage> orMap = (ORMap<String, CrdtMessage>) changed.dataValue();
                orMap.getEntries().forEach((k, v) -> {
                   log.info("crdt-key:" + k + " crdt-message:" + v.getMessage());
                });
            }
        } else if (message instanceof ClusterMessages.ReadAllData) {
            CrdtQueries.readAllMessages().thenAccept(map -> {
                map.getEntries().forEach((k, v) -> {
                    log.info("read-key:" + k + " read-message:" + v.getMessage());
                });
            });
        } else if (message instanceof ClusterMessages.GetData) {

            ClusterMessages.GetData getData = (ClusterMessages.GetData) message;
            CrdtQueries.readAllMessages().thenAccept(map -> {
                log.info("get-key:" + getData.getKey() + " get-message:" + map.get(getData.getKey()).get().getMessage());
            });
        } else if (message instanceof ClusterMessages.UpdateData) {

            ClusterMessages.UpdateData updateData = (ClusterMessages.UpdateData) message;
            CrdtQueries.updateMessage(updateData.getCrdtMessage(), node).thenAccept(res -> {
                if (res instanceof Replicator.UpdateSuccess) {
                    Replicator.UpdateSuccess updateSuccess = (Replicator.UpdateSuccess) res;
                    CrdtMessage fileMessage = (CrdtMessage) updateSuccess.getRequest().get();
                    log.info("update-key:" + fileMessage.getKey() + " update-message:" + fileMessage.getMessage());
                }
            });
        } else if (message instanceof ClusterMessages.RemoveData) {

            ClusterMessages.RemoveData removeDataData = (ClusterMessages.RemoveData) message;
            CrdtQueries.removeMessage(removeDataData.getCrdtMessage(), node).thenAccept(res -> {

            });
        }
    }
}
