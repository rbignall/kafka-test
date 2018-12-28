package com.howtoprogram.kafka;

import java.util.Map;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

public class EnrolmentRequestSerde implements Serde<EnrolmentRequest> {

	private EnrolmentRequestSerializer erSerializer = null;
	private EnrolmentRequestDeserializer erDeserializer = null;
	
	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {
		erSerializer = new EnrolmentRequestSerializer();
		erSerializer.configure(configs, isKey);
		erDeserializer = new EnrolmentRequestDeserializer();
		erDeserializer.configure(configs, isKey);
	}

	@Override
	public Serializer<EnrolmentRequest> serializer() {
		return erSerializer;
	}

	@Override
	public Deserializer<EnrolmentRequest> deserializer() {
		return erDeserializer;
	}

	@Override
	public void close() {
		erSerializer.close();
		erDeserializer.close();
	}

}
