package com.howtoprogram.kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class ProducerTest {

  public static void main(String[] args) {
    Properties props = new Properties();
    props.put("bootstrap.servers", "localhost:9092,localhost:9093,localhost:9094");
    props.put("acks", "all");
    props.put("retries", 0);
    props.put("batch.size", 16384);
    props.put("linger.ms", 1);
    props.put("buffer.memory", 33554432);
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    props.put("value.serializer", "com.howtoprogram.kafka.EnrolmentRequestSerializer");
    
    Producer<String, EnrolmentRequest> producer = null;
    
    try {
      producer = new KafkaProducer<>(props);
      for (int i = 0; i < 1; i++) {
    	  EnrolmentRequest request = new EnrolmentRequest();
    	  request.setCustomerName("Java Customer");
    	  request.setNarrative("Let's hope this serialization thing works");
    	  request.setStatus(RequestStatus.Unmatched);
    	  
    	  producer.send(new ProducerRecord<String, EnrolmentRequest>("unmatched-requests", request));
    	  System.out.println("Sent: " + request.toString());
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      producer.close();
    }

  }

}
