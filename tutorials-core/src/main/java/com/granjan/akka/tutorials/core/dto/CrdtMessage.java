package com.granjan.akka.tutorials.core.dto;

import akka.cluster.ddata.ReplicatedData;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class CrdtMessage implements ReplicatedData, Serializable {

    String key;
    String message;

    @Override
    public ReplicatedData merge(ReplicatedData that) {
        return that;
    }
}
