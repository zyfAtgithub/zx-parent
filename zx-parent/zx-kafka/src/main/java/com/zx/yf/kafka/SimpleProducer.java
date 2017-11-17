//package com.zx.yf.kafka;
//import org.apache.kafka.clients.producer.*;
//import org.junit.Test;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.Properties;
//
//public class SimpleProducer {
//    @Test
//    public void testProduce(){
//        Properties props = new Properties();
//        props.put("bootstrap.servers", "10.0.0.30:9092");
//        props.put("acks", "all");
//        props.put("retries", 0);
//        props.put("batch.size", 16384);
//        props.put("linger.ms", 1);
//        props.put("buffer.memory", 33554432);
//        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//        props.put(ProducerConfig.METADATA_FETCH_TIMEOUT_CONFIG, "3000");
//        KafkaProducer<String , String> producer = new KafkaProducer<String, String>(props);
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//        for(int i = 0; i < 5; i++) {
//            ProducerRecord<String,String> record = new ProducerRecord<String, String>("test1234", i + "", simpleDateFormat.format(new Date()) + "---" + i);
//            producer.send(record ,
//                    new Callback() {
//                        public void onCompletion(RecordMetadata metadata, Exception e) {
//                            if(e != null)
//                                e.printStackTrace();
//                            System.out.println("The offset of the record we just sent is: " + metadata.offset());
//                        }
//                    });
//        }
//        producer.close();
//    }
//}
