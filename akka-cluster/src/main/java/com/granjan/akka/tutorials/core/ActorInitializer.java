package com.granjan.akka.tutorials.core;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.stream.ActorMaterializer;
import akka.stream.Materializer;
import com.akka.tutorials.crdt.CrdtActor;
import com.akka.tutorials.crdt.CrdtQueries;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class ActorInitializer {
    private static Config config;
    private static ActorSystem actorSystem;
    private static Materializer mat;
    static ActorRef crdtActor;

    public static void init() {
        config = setConfig();
        actorSystem = ActorSystem.create("akka-java-tutorials", config);
        mat = ActorMaterializer.create(actorSystem);
        CrdtQueries.init(actorSystem);
        crdtActor = actorSystem.actorOf(CrdtActor.props(actorSystem));
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
