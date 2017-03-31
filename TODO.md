* Update documentation - including javadoc on the methods that change (especially the methods that is part of the interface to "the outside")
* Make sure nothing is using Serializer.serialize directly - use serializeByteBuffer instead
* Make sure nothing is using the Partitioner.partition that takes byte-arrays - use the one taking ByteBuffers instead
* Add test that verifies
  * Given a Serializer S that is implemented via the "old" byte-array producing serialize method
  * and a Partitioner P that is implemented via the "old" byte-array-parameters partition method
  * and a KafkaProcucer KP that uses S (for both key and value) and P
  * When KP is called with a ProducerRecord without any partition set
  * Then the "old" byte-array production serialize method on S is called twice, once with the key and once with the value
  * and the "old" byte-array-parameters partition method on P is called
  * and the key/value-byte-arrays handed over to the partitioner are the same as the once produced by S - not equal but THE SAME
* Make sure all Serializers that come with Kafka itself is implemented using the new serializeByteBuffer method - or maybe not, people out there, may be inheriting from those overriding the "old", so if we change them to have a serializeByteBuffer method that does not call the old serialize method, then those overrides will not come into play, breaking backwards compatibility. If we are allowed to do that, we should get rid of the byte-array variants completely
* Make sure all Partitioners that come with Kafka itself is implemented using the new byte-buffer-parameters method - or maybe not,..... se above 
* Consider doing the same change for the consuming side, so that fetcher does not have to convert the ByteBuffers it receive to byte-arrays
* Double-check by code-inspection (or new tests) that it seems the key/value-ByteBuffers are not read using relative reads (moving position) or that, if they are, they are rewinded afterwards, so that other parts of the system can also read them