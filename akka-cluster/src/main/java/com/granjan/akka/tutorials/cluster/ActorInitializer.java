package com.granjan.akka.tutorials.cluster;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.PoisonPill;
import akka.cluster.singleton.ClusterSingletonManager;
import akka.cluster.singleton.ClusterSingletonManagerSettings;
import akka.cluster.singleton.ClusterSingletonProxy;
import akka.cluster.singleton.ClusterSingletonProxySettings;
import akka.stream.ActorMaterializer;
import akka.stream.Materializer;
import com.granjan.akka.tutorials.cluster.singleton.actor.ClusterSingletonActor;
import com.granjan.akka.tutorials.distributed.data.crdt.CrdtActor;
import com.granjan.akka.tutorials.distributed.data.crdt.CrdtQueries;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import scala.Option;
import scala.concurrent.duration.FiniteDuration;

import java.util.concurrent.TimeUnit;

public class ActorInitializer {
    private static Config config;
    private static ActorSystem actorSystem;
    private static Materializer mat;
    static ActorRef crdtActor;
    private static ActorRef clusterProxy;

    public static void init() {
        config = setConfig();
        actorSystem = ActorSystem.create("akka-java-tutorials", config);
        mat = ActorMaterializer.create(actorSystem);
        CrdtQueries.init(actorSystem);
        crdtActor = actorSystem.actorOf(CrdtActor.props(actorSystem));

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

        clusterProxy = actorSystem.actorOf(ClusterSingletonProxy.props(
                "/user/" + "ClusterSingletonActorManager",
                proxySettings),
                "ClusterSingletonActorProxy");
    }

    private static Config setConfig() {
        Config configAll = ConfigFactory.load();
        Config envq = configAll.getConfig("development");
        String address = "127.0.0.1";

        Config common = configAll.getConfig("common");
        return ConfigFactory.parseString(
                "akka.remote.netty.tcp.hostname=" + address).withFallback(envq).withFallback(common);
    }

    public static ActorRef getCrdtActor() {
        return crdtActor;
    }
}
