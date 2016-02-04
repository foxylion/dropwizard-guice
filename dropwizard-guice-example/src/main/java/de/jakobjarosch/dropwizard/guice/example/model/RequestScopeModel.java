package de.jakobjarosch.dropwizard.guice.example.model;

import com.google.inject.servlet.RequestScoped;

@RequestScoped
public class RequestScopeModel {

	private String ipAddress;

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getIpAddress() {
		return ipAddress;
	}
}
