package com.granjan.akka.tutorials.distributed.data.crdt;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.cluster.ddata.*;
import com.granjan.akka.tutorials.core.dto.CrdtMessage;

import java.time.Duration;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static akka.pattern.Patterns.ask;

public class CrdtQueries {

    final static Replicator.WriteConsistency writeAll = new Replicator.WriteAll(Duration.ofSeconds(5));
    final static Replicator.ReadConsistency readAll = new Replicator.ReadAll(Duration.ofSeconds(5));
    static ActorRef replicator;
    private static final Key<ORMap<String, CrdtMessage>> KEY = ORMapKey.create("crdt-tutorial");
    private static final Duration timeoutCache = Duration.ofSeconds(5);

    public static void init(ActorSystem actorSystem) {
        replicator = DistributedData.get(actorSystem).replicator();
    }

    /**
     * Read the complete map contents
     *
     * @return ormap completion stage object
     */
    public static CompletionStage<ORMap<String, CrdtMessage>> readAllMessages() {
        CompletableFuture<Object> messageMap = ask(replicator,
                new Replicator.Get<ORMap<String, CrdtMessage>>(KEY, readAll),
                timeoutCache).toCompletableFuture();
        return messageMap.<ORMap<String, CrdtMessage>>thenApply(respobjeçt -> {
            if (respobjeçt instanceof Replicator.GetSuccess) {
                Replicator.GetSuccess getSuccess = (Replicator.GetSuccess<ORMap<String, CrdtMessage>>) respobjeçt;
                return ((ORMap<String, CrdtMessage>) getSuccess.dataValue());
            } else {
                return ORMap.create();
            }
        });
    }

    /**
     * Update the message in the çrdt.data store
     *
     * @param crdtMessage
     * @param node
     * @return
     */
    public static CompletableFuture<Object> updateMessage(CrdtMessage crdtMessage, SelfUniqueAddress node) {
        Replicator.Update<ORMap<String, CrdtMessage>> update =
                new Replicator.Update<>(KEY, ORMap.create(), writeAll,
                        Optional.of(crdtMessage),
                        map -> map.put(node, crdtMessage.getKey(), crdtMessage));
        return ask(replicator,
                update,
                timeoutCache).toCompletableFuture();
    }

    /**
     * Remove the message in the çrdt.data store
     *
     * @param crdtMessage
     * @param node
     * @return
     */
    public static CompletableFuture<Object> removeMessage(CrdtMessage crdtMessage, SelfUniqueAddress node) {
        Replicator.Update<ORMap<String, CrdtMessage>> update =
                new Replicator.Update<>(KEY, ORMap.create(), writeAll,
                        Optional.of(crdtMessage),
                        map -> map.remove(node, crdtMessage.getKey()));
        return ask(replicator,
                update,
                timeoutCache).toCompletableFuture();
    }

}


