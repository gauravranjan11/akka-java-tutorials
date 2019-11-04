package com.granjan.akka.tutorials.cluster.singleton.actor;

import akka.actor.ActorSystem;
import akka.actor.PoisonPill;
import akka.cluster.singleton.ClusterSingletonManager;
import akka.cluster.singleton.ClusterSingletonManagerSettings;
import akka.cluster.singleton.ClusterSingletonProxy;
import akka.cluster.singleton.ClusterSingletonProxySettings;
import scala.Option;
import scala.concurrent.duration.FiniteDuration;

import java.util.concurrent.TimeUnit;

public class ClusterSingletonRunner {

    public static void setUpSingleton(ActorSystem actorSystem) {
        ClusterSingletonManagerSettings manageSettings = ClusterSingletonManagerSettings
                .create(actorSystem).withSingletonName("ClusterSingletonActorManager").withHandOverRetryInterval(new FiniteDuration(6, TimeUnit.SECONDS))
                .withRemovalMargin(new FiniteDuration(6, TimeUnit.SECONDS));

        actorSystem.actorOf(ClusterSingletonManager
                        .props(ClusterSingletonActor
                                        .props(actorSystem),
                                PoisonPill.getInstance(),
                                manageSettings),
                "ClusterSingletonActorManager");

        ClusterSingletonProxySettings proxySettings = new ClusterSingletonProxySettings(
                "ClusterSingletonActorManager", Option.empty(),
                new FiniteDuration(1, TimeUnit.SECONDS), 1000);

        actorSystem.actorOf(ClusterSingletonProxy.props(
                "/user/" + "ClusterSingletonActorManager",
                proxySettings),
                "ClusterSingletonActorProxy");
    }
}
