package de.jakobjarosch.dropwizard.guice;

import javax.inject.Singleton;
import javax.sql.DataSource;

import org.skife.jdbi.v2.DBI;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.eventbus.EventBus;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.servlet.ServletModule;

import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Environment;

public class DropwizardModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new ServletModule());
	}

	@Provides
	@Singleton
	public EventBus provideEventBus() {
		return new EventBus();
	}

	@Provides
	public ObjectMapper provideObjectMapper() {
		return new ObjectMapper();
	}

	@Provides
	@Singleton
	public DBI provideDbi(GuiceConfiguration config, Environment environment) {
		return new DBIFactory().build(environment, config.getDatabase(), "jdbi");
	}

	@Provides
	@Singleton
	public DataSource provideFlywayDataSource(GuiceConfiguration config) {
		return config.getDatabase().build(null, "datasource");
	}
}
