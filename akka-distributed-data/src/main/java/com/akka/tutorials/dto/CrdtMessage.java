package com.akka.tutorials.dto;

import akka.cluster.ddata.ReplicatedData;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CrdtMessage implements ReplicatedData {

    String key;
    String message;

    @Override
    public ReplicatedData merge(ReplicatedData that) {
        return that;
    }
}
