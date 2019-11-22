package com.granjan.akka.tutorials.cluster;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.stream.ActorMaterializer;
import akka.stream.Materializer;
import akka.stream.alpakka.slick.javadsl.SlickSession;
import com.granjan.akka.tutorials.distributed.data.crdt.CrdtActor;
import com.granjan.akka.tutorials.distributed.data.crdt.CrdtQueries;
import com.granjan.akka.tutorials.distributed.publisher.Publisher;
import com.granjan.akka.tutorials.distributed.subscriber.Subscriber;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class ActorInitializer {
    private static Config config;
    private static ActorSystem actorSystem;
    private static Materializer mat;
    static ActorRef crdtActor;
    static ActorRef publisher;
    static ActorRef subscriber;
    private static ActorRef clusterProxy;
    private static SlickSession slickSession;

    public static void init() {
        config = setConfig();
        actorSystem = ActorSystem.create("akka-java-tutorials", config);
        mat = ActorMaterializer.create(actorSystem);
        slickSession = SlickSession.forConfig(config.getConfig("slick-h2"));
        CrdtQueries.init(actorSystem);
        crdtActor = actorSystem.actorOf(CrdtActor.props(actorSystem));
        publisher = actorSystem.actorOf(Publisher.props(actorSystem));
        subscriber = actorSystem.actorOf(Subscriber.props(actorSystem));

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

    public static ActorSystem getActorSystem() {
        return actorSystem;
    }

    public static SlickSession getSlickSession() {
        return slickSession;
    }

    public static Materializer getMat() {
        return mat;
    }
}
