package com.howtoprogram.kafka;

import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class StatusValueSerializer extends JsonSerializer<RequestStatus> {

	@Override
	public void serialize(RequestStatus value, JsonGenerator jsonGenerator, SerializerProvider serializers) throws IOException {

		switch (value) {
		case Unmatched :
			jsonGenerator.writeNumber(0);
			break;
		case Matched:
			jsonGenerator.writeNumber(1);
			break;
		case Referred:
			jsonGenerator.writeNumber(2);
			break;
		case Completed:
			jsonGenerator.writeNumber(3);
		default:
			jsonGenerator.writeNull();
			break;
		}
		
	}
}
