package com.granjan.akka.tutorials.cluster.singleton.actor;

import akka.actor.*;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.granjan.akka.tutorials.core.dto.ClusterMessages;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ClusterSingletonActor extends UntypedAbstractActor {

    ActorSystem actorSystem;
    Cancellable singletonScheduler;
    LoggingAdapter log;


    public static Props props(ActorSystem actorSystem) {
        return Props.create(ClusterSingletonActor.class,
                actorSystem);
    }

    public ClusterSingletonActor(ActorSystem actorSystem) {
        this.actorSystem = actorSystem;
        this.log = Logging.getLogger(actorSystem, ClusterSingletonActor.class);

    }

    @Override
    public void preStart() {
        schedule();
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof ClusterMessages.SingletonScheduler) {
            log.info("Scheduler ran after 20 seconds");
        }
    }

    private void schedule() {
        this.singletonScheduler = actorSystem.scheduler().schedule(
                scala.concurrent.duration.Duration.create(new Random().nextInt(5), TimeUnit.SECONDS),
                scala.concurrent.duration.Duration.create(20, TimeUnit.SECONDS),
                new Runnable() {
                    @Override
                    public void run() {
                        self().tell(new ClusterMessages.SingletonScheduler(), ActorRef.noSender());
                    }
                }, actorSystem.dispatcher()

        );
    }
}
