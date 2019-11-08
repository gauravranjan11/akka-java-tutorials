package com.granjan.akka.tutorials.distributed.subscriber;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedAbstractActor;
import akka.cluster.pubsub.DistributedPubSub;
import akka.cluster.pubsub.DistributedPubSubMediator;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.granjan.akka.tutorials.core.dto.ClusterMessages;

public class Subscriber extends UntypedAbstractActor {

    ActorRef mediator;
    ActorSystem actorSystem;
    LoggingAdapter log;

    public static Props props(ActorSystem actorSystem) {
        return Props.create(Subscriber.class,
                actorSystem);
    }

    public Subscriber(ActorSystem actorSystem) {
        this.actorSystem = actorSystem;
        this.mediator = DistributedPubSub.get(actorSystem).mediator();
        log = Logging.getLogger(actorSystem, Subscriber.class);
        this.mediator.tell(new DistributedPubSubMediator.Subscribe("akka-tutorials", getSelf()), getSelf());
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof ClusterMessages.PublishMessage) {
            log.info("message received from publisher in akka cluster");
        }
    }
}
