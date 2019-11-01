package com.granjan.akka.tutorials;

import akka.actor.ActorRef;
import com.akka.tutorials.core.ClusterMessages;
import com.akka.tutorials.dto.CrdtMessage;
import com.granjan.akka.tutorials.core.ActorInitializer;

import static com.akka.tutorials.core.ClusterMessages.UpdateData;

public class Driver {
    public static void main(String[] args) throws InterruptedException {
        ActorInitializer.init();
        runCrdtQueries();
    }

    static void runCrdtQueries() throws InterruptedException {

        //add data
        ActorInitializer.getCrdtActor().tell(UpdateData.builder().crdtMessage(CrdtMessage.builder().
                key("first").message("firstMessage").
                build()).build(), ActorRef.noSender());
        ActorInitializer.getCrdtActor().tell(UpdateData.builder().crdtMessage(CrdtMessage.builder().
                key("second").message("secondMessage").
                build()).build(), ActorRef.noSender());
        ActorInitializer.getCrdtActor().tell(UpdateData.builder().crdtMessage(CrdtMessage.builder().
                key("third").message("thirdMessage").
                build()).build(), ActorRef.noSender());

        //get data
        ActorInitializer.getCrdtActor().tell(ClusterMessages.GetData.builder().key("first").build(), ActorRef.noSender());

        //remove data
        ActorInitializer.getCrdtActor().tell(ClusterMessages.RemoveData.builder().crdtMessage(CrdtMessage.builder().
                key("third").message("thirdMessage").
                build()).build(), ActorRef.noSender());

        ActorInitializer.getCrdtActor().tell(UpdateData.builder().crdtMessage(CrdtMessage.builder().
                key("fourth").message("fourthMessage").
                build()).build(), ActorRef.noSender());


    }
}
