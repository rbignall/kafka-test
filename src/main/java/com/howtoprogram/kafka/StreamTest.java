package com.howtoprogram.kafka;

import java.util.Properties;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;

public class StreamTest {

	public static void main(String[] args) {		
	    Properties props = new Properties();
	    props.put("bootstrap.servers", "localhost:9092,localhost:9093,localhost:9094");
	    props.put("application.id", "java-streamer-application");
	    props.put("state.dir", "C:\\Java\\eclipse-workspace\\Temp");
	    props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
	    props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, EnrolmentRequestSerde.class);

	    StreamsBuilder builder = new StreamsBuilder();
	    KStream<String, EnrolmentRequest> unmatchedRequests = builder.stream("unmatched-requests");
	    KStream<String, EnrolmentRequest> attemptedRequests = unmatchedRequests.mapValues(new EnrolmentRequestValueMapper());
	    attemptedRequests.filter((k, v) -> v.getStatus() == RequestStatus.Matched).to("matched-requests");
	    attemptedRequests.filter((k, v) -> v.getStatus() == RequestStatus.Referred).to("referred-requests");
	    KafkaStreams streams = new KafkaStreams(builder.build(), props);

	    streams.start();
		System.out.println("OK I'm listening now");

	    Boolean keepRunning = true;
	    while (keepRunning) {
	        try {
		      Thread.sleep(1000);
	      	  if (System.in.available() != 0) {
	      		  keepRunning = false;
	      	  }
	        }
	        catch (Exception e) {
	      	  System.out.println("Exception checking for input: " + e.getMessage());
	      	  keepRunning = false;
	        }
	    }

	    streams.close();
		System.out.println("Thanks I've finished");
	}

}
