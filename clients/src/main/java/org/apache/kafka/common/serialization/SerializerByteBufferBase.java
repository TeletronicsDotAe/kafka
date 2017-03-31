package org.apache.kafka.common.serialization;

import java.nio.ByteBuffer;

public abstract class SerializerByteBufferBase<T> implements Serializer<T> {

    @Override
    abstract  public ByteBuffer serializeByteBuffer(String topic, T data);

    @Override
    public byte[] serialize(String topic, T data) {
        throw new RuntimeException("Do not use byte-array returning serialize method when extending " + SerializerByteBufferBase.class.getSimpleName() + ". Programmers error");
    }

}
