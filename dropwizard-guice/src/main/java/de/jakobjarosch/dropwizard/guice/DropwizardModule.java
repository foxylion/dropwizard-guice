package de.jakobjarosch.dropwizard.guice;

import javax.inject.Singleton;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.eventbus.EventBus;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.servlet.ServletModule;

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
}
