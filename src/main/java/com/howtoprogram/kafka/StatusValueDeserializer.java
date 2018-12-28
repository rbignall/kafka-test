package com.howtoprogram.kafka;

import java.io.IOException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class StatusValueDeserializer extends JsonDeserializer<RequestStatus> {

	@Override
	public RequestStatus deserialize(JsonParser jsonParser, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {

        String text = jsonParser.getText();
        switch (text) {
        case "0" :
        	return RequestStatus.Unmatched;
        case "1" :
        	return RequestStatus.Matched;
        case "2" :
        	return RequestStatus.Referred;
        case "3" :
        	return RequestStatus.Completed;
        default :
        	return null;
        }
	}

}
