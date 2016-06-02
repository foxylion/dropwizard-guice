package de.jakobjarosch.dropwizard.guice.example.config;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;

public class ExampleConfiguration extends Configuration {

	@JsonProperty(defaultValue = "Hello World!")
	String greeting;

	public String getGreeting() {
		return greeting;
	}
}
