package com.zx.yf.kafka;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.message.MessageAndMetadata;

public class KafkaConsumerExample {
 
     
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("zookeeper.connect", "10.0.0.30:2181");
        props.put("group.id", "1111");
        props.put("auto.offset.reset", "smallest");
        ConsumerConfig conf = new ConsumerConfig(props);
        ConsumerConnector consumer = Consumer.createJavaConsumerConnector(conf);
        Map<String, Integer> topicStrams = new HashMap<String, Integer>();
        topicStrams.put("test", 1);
        Map<String, List<KafkaStream<byte[], byte[]>>> messageStreamsMap = consumer.createMessageStreams(topicStrams);
        List<KafkaStream<byte[], byte[]>> messageStreams = messageStreamsMap.get("test");
        for(final KafkaStream<byte[], byte[]> kafkaStream : messageStreams){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for(MessageAndMetadata<byte[], byte[]> mm : kafkaStream){
                        String msg = new String(mm.message());
                        System.out.println(msg);
                    }
                }
             
            }).start();
         
        }
    }
}