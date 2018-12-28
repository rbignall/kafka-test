package com.howtoprogram.kafka;

import java.util.Map;
import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EnrolmentRequestSerializer implements Serializer<EnrolmentRequest> {

	private ObjectMapper objectMapper = null;
	
	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {
		objectMapper = new ObjectMapper();
	}

	@Override
	public byte[] serialize(String topic, EnrolmentRequest data) {
		try {
			return objectMapper.writeValueAsBytes(data);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void close() {
		// Nothing to do here
	}

}
