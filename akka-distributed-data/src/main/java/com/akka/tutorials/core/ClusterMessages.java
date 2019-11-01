package com.akka.tutorials.core;

import com.akka.tutorials.dto.CrdtMessage;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

public class ClusterMessages implements Serializable {


    public static class ReadAllData extends ClusterMessages {

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
