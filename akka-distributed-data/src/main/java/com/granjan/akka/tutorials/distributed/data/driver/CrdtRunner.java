package com.granjan.akka.tutorials.distributed.data.driver;

import akka.actor.ActorRef;
import com.granjan.akka.tutorials.core.dto.CrdtMessage;

import static com.granjan.akka.tutorials.core.dto.ClusterMessages.*;

public class CrdtRunner {

    public static void runCrdtQueries(ActorRef actorRef) throws InterruptedException {

        //add data
        actorRef.tell(UpdateData.builder().crdtMessage(CrdtMessage.builder().
                key("first").message("firstMessage").
                build()).build(), ActorRef.noSender());
        actorRef.tell(UpdateData.builder().crdtMessage(CrdtMessage.builder().
                key("second").message("secondMessage").
                build()).build(), ActorRef.noSender());
        actorRef.tell(UpdateData.builder().crdtMessage(CrdtMessage.builder().
                key("third").message("thirdMessage").
                build()).build(), ActorRef.noSender());

        //get data
        actorRef.tell(GetData.builder().key("first").build(), ActorRef.noSender());

        //remove data
        actorRef.tell(RemoveData.builder().crdtMessage(CrdtMessage.builder().
                key("third").message("thirdMessage").
                build()).build(), ActorRef.noSender());

        actorRef.tell(UpdateData.builder().crdtMessage(CrdtMessage.builder().
                key("fourth").message("fourthMessage").
                build()).build(), ActorRef.noSender());


    }
}
