package de.jakobjarosch.dropwizard.guice;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;
import io.dropwizard.db.PooledDataSourceFactory;

public class GuiceConfiguration extends Configuration {

	@Valid
	@JsonProperty
	private PooledDataSourceFactory database;
	
	public PooledDataSourceFactory getDatabase() {
		return database;
	}
}
