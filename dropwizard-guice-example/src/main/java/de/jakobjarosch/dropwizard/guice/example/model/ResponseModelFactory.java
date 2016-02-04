package de.jakobjarosch.dropwizard.guice.example.model;

import javax.inject.Inject;
import javax.inject.Provider;

import de.jakobjarosch.dropwizard.guice.example.ExampleGuiceModule.TrailingPunctation;
import de.jakobjarosch.dropwizard.guice.example.config.ExampleConfiguration;

public class ResponseModelFactory {

	private final ExampleConfiguration config;
	private final String puncatation;
	private final CounterStore counter;
	private final Provider<RequestScopeModel> requestScope;

	@Inject
	public ResponseModelFactory(ExampleConfiguration config, @TrailingPunctation String puncatation, CounterStore counter,
			Provider<RequestScopeModel> requestScope) {
		this.config = config;
		this.puncatation = puncatation;
		this.counter = counter;
		this.requestScope = requestScope;
	}

	public ResponseModel create() {
		String message = config.getGreeting() + puncatation;
		long counterValue = counter.get();
		String ipAddress = requestScope.get().getIpAddress();
		return new ResponseModel(message, counterValue, ipAddress);
	}
}
