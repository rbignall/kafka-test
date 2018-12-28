package com.howtoprogram.kafka;

import java.io.IOException;
import java.util.Map;
import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EnrolmentRequestDeserializer implements Deserializer<EnrolmentRequest> {

	private ObjectMapper objectMapper = null;

	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {
		objectMapper = new ObjectMapper();
	}

	@Override
	public EnrolmentRequest deserialize(String topic, byte[] data) {
		try {
			return objectMapper.readValue(data, EnrolmentRequest.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void close() {
		// Nothing to do here
	}

}
