package com.howtoprogram.kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class BasicProducerTest {

  public static void main(String[] args) {
    Properties props = new Properties();
    props.put("bootstrap.servers", "localhost:9092,localhost:9093,localhost:9094");
    props.put("acks", "all");
    props.put("retries", 0);
    props.put("batch.size", 16384);
    props.put("linger.ms", 1);
    props.put("buffer.memory", 33554432);
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    
    Producer<String, String> producer = null;
    
    try {
      producer = new KafkaProducer<>(props);
      for (int i = 0; i < 1; i++) {
    	  String request = "Let's hope this serialization thing works";    	  
    	  producer.send(new ProducerRecord<String, String>("new-test-topic", request));
    	  System.out.println("Sent: " + request.toString());
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      producer.close();
    }

  }

}
