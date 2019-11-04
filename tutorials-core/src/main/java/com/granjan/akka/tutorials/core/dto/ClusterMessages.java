package com.granjan.akka.tutorials.core.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

public class ClusterMessages implements Serializable {


    public static class ReadAllData extends ClusterMessages {

    }

    public static class SingletonScheduler extends ClusterMessages {

    }

    @Data
    @Builder
    public static class RemoveData extends ClusterMessages {
         CrdtMessage crdtMessage;

    }

    @Data
    @Builder
    public static class UpdateData extends ClusterMessages {
         CrdtMessage crdtMessage;

    }

    @Data
    @Builder
    public static class GetData extends ClusterMessages {
         String key;
    }
}
