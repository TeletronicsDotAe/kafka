package org.apache.kafka.clients.producer;

import org.apache.kafka.common.Cluster;

import java.nio.ByteBuffer;

public abstract class ByteBufferPartitionerBase implements Partitioner {

    @Override
    abstract public int partition(String topic, Object key, ByteBuffer keyBytes, Object value, ByteBuffer valueBytes, Cluster cluster);

    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        throw new RuntimeException("Do not use byte-array partition method when extending " + ByteBufferPartitionerBase.class.getSimpleName() + ". Programmers error");
    }

}
