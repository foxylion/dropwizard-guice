package de.jakobjarosch.dropwizard.guice.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseModel {

	@JsonProperty
	private final String message;
	
	@JsonProperty
	private long requestCount;

	@JsonProperty
	private String ipAddress;

	public ResponseModel(String message, long requestCount, String ipAddress) {
		this.message = message;
		this.requestCount = requestCount;
		this.ipAddress = ipAddress;
	}
}
