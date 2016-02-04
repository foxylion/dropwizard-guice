package de.jakobjarosch.dropwizard.guice.example.config;

import com.fasterxml.jackson.annotation.JsonProperty;

import de.jakobjarosch.dropwizard.guice.GuiceConfiguration;

public class ExampleConfiguration extends GuiceConfiguration {

	@JsonProperty(defaultValue = "Hello World!")
	String greeting;

	public String getGreeting() {
		return greeting;
	}
}
