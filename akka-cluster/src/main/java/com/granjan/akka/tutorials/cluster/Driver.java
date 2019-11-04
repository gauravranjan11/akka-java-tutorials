package com.granjan.akka.tutorials.cluster;

import com.granjan.akka.tutorials.distributed.data.driver.CrdtRunner;

public class Driver {
    public static void main(String[] args) throws InterruptedException {
        ActorInitializer.init();
        CrdtRunner.runCrdtQueries(ActorInitializer.getCrdtActor());
    }

}
