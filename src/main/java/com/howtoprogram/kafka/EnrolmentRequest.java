package com.howtoprogram.kafka;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class EnrolmentRequest {

    private String customerName;
    private String customerId;
    private String narrative;
    private RequestStatus status;
    private String outcome;
    
	@JsonGetter("CustomerName")
	public String getCustomerName() {
		return customerName;
	}
	
	@JsonSetter("CustomerName")
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	@JsonGetter("CustomerId")
	public String getCustomerId() {
		return customerId;
	}
	
	@JsonSetter("CustomerId")
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	@JsonGetter("Narrative")
	public String getNarrative() {
		return narrative;
	}
	
	@JsonSetter("Narrative")
	public void setNarrative(String narrative) {
		this.narrative = narrative;
	}

	@JsonGetter("Status")
	@JsonSerialize(using = StatusValueSerializer.class)
	public RequestStatus getStatus() {
		return status;
	}
	
	@JsonSetter("Status")
	@JsonDeserialize(using = StatusValueDeserializer.class)
	public void setStatus(RequestStatus status) {
		this.status = status;
	}

	@JsonGetter("Outcome")
	public String getOutcome() {
		return outcome;
	}
	
	@JsonSetter("Outcome")
	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}

	@Override
	public String toString() {
		return "EnrolmentRequest [customerName=" + customerName + ", customerId=" + customerId + ", narrative="
				+ narrative + ", status=" + status + ", outcome=" + outcome + "]";
	}

}
