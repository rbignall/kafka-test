package com.howtoprogram.kafka;

import org.apache.kafka.streams.kstream.ValueMapper;

public class EnrolmentRequestValueMapper implements ValueMapper<EnrolmentRequest, EnrolmentRequest> {

	@Override
	public EnrolmentRequest apply(EnrolmentRequest value) {
		String[] splitCustomer = value.getCustomerName().split(" ");
		Boolean foundTag = false;
    	value.setStatus(RequestStatus.Referred);

		for (String part : splitCustomer) {
			if (foundTag) {
		    	value.setCustomerId(part);
		    	value.setStatus(RequestStatus.Matched);
		    	break;
			}
			else if (part.equalsIgnoreCase("customer")) {
				foundTag = true;
			}
		}

		return value;
	}

}
