package com.granjan.akka.tutorials.cluster;

import com.granjan.akka.tutorials.cluster.singleton.actor.ClusterSingletonRunner;
import com.granjan.akka.tutorials.distributed.data.driver.CrdtRunner;
import com.granjan.akka.tutorials.http.RestCalls;
import com.granjan.akka.tutorials.slick.jdbc.QueryRunner;

public class Driver {
    public static void main(String[] args) throws InterruptedException {
        ActorInitializer.init();
        CrdtRunner.runCrdtQueries(ActorInitializer.getCrdtActor());
        ClusterSingletonRunner.setUpSingleton(ActorInitializer.getActorSystem());
        QueryRunner.init(ActorInitializer.getSlickSession(),ActorInitializer.getMat(),ActorInitializer.getActorSystem());
        RestCalls.init(ActorInitializer.getMat(),ActorInitializer.getActorSystem());
    }

}
