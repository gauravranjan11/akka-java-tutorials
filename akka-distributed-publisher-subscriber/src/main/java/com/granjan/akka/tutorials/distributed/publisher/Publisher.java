package com.granjan.akka.tutorials.distributed.publisher;

import akka.actor.*;
import akka.cluster.pubsub.DistributedPubSub;
import akka.cluster.pubsub.DistributedPubSubMediator;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.granjan.akka.tutorials.core.dto.ClusterMessages;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Publisher extends UntypedAbstractActor {

    ActorRef mediator;
    ActorSystem actorSystem;
    LoggingAdapter log;
    Cancellable messagePublisher;

    @Override
    public void preStart() {
        schedule();
    }

    public static Props props(ActorSystem actorSystem) {
        return Props.create(Publisher.class,
                actorSystem);
    }
    public Publisher(ActorSystem actorSystem) {
        this.actorSystem = actorSystem;
        this.mediator = DistributedPubSub.get(actorSystem).mediator();
        log = Logging.getLogger(actorSystem, Publisher.class);
    }

    @Override
    public void onReceive(Object message) throws Throwable {

        if (message instanceof ClusterMessages.PublishMessage) {
            log.info("publishing message in akka cluster");
            this.mediator.tell(new DistributedPubSubMediator.Publish("akka-tutorials", message), getSelf());
        }
    }

    private void schedule() {
        this.messagePublisher = actorSystem.scheduler().schedule(
                scala.concurrent.duration.Duration.create(new Random().nextInt(5), TimeUnit.SECONDS),
                scala.concurrent.duration.Duration.create(22, TimeUnit.SECONDS),
                new Runnable() {
                    @Override
                    public void run() {
                        self().tell(new ClusterMessages.PublishMessage(), ActorRef.noSender());
                    }
                }, actorSystem.dispatcher()

        );
    }
}
