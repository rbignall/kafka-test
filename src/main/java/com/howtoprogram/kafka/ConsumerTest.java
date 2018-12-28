package com.howtoprogram.kafka;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class ConsumerTest {

  public static void main(String[] args) {
    Properties props = new Properties();
    props.put("bootstrap.servers", "localhost:9092,localhost:9093,localhost:9094");
    props.put("group.id", "java-consumer-group");
    props.put("enable.auto.commit", "true");
    props.put("auto.commit.interval.ms", "1000");
    props.put("auto.offset.reset", "earliest");
    props.put("session.timeout.ms", "30000");
    props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
    props.put("value.deserializer", "com.howtoprogram.kafka.EnrolmentRequestDeserializer");

    KafkaConsumer<String, EnrolmentRequest> kafkaConsumer = new KafkaConsumer<>(props);
    kafkaConsumer.subscribe(Arrays.asList("referred-requests"));

    Duration pollTimeout = Duration.ofSeconds(1);
    Boolean keepRunning = true;
    while (keepRunning) {
      ConsumerRecords<String, EnrolmentRequest> records = kafkaConsumer.poll(pollTimeout);
      
      for (ConsumerRecord<String, EnrolmentRequest> record : records) {
        System.out.println("Partition: " + record.partition() + " Offset: " + record.offset()
            + " Value: " + record.value());
      }

      try {
    	  if (System.in.available() != 0) {
    		  keepRunning = false;
    	  }
      }
      catch (Exception e) {
    	  System.out.println("Exception checking for input: " + e.getMessage());
    	  keepRunning = false;
      }
    }
    
    kafkaConsumer.close();

    System.out.println("OK I've finished");
  }

}
