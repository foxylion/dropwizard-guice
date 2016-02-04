package de.jakobjarosch.dropwizard.guice.example;

import javax.inject.Inject;

import de.jakobjarosch.dropwizard.guice.GuiceApplication;
import de.jakobjarosch.dropwizard.guice.GuiceBootstrap;
import de.jakobjarosch.dropwizard.guice.example.config.ExampleConfiguration;
import de.jakobjarosch.dropwizard.guice.example.resource.ExampleResource;
import io.dropwizard.setup.Environment;

public class ExampleApplication implements GuiceApplication {

	public static void main(String[] args) throws Exception {
		new GuiceBootstrap<ExampleConfiguration>(ExampleConfiguration.class, ExampleApplication.class, new ExampleGuiceModule()) {
		}.run(args);
	}

	private final Environment environment;
	private final ExampleResource exampleResource;

	@Inject
	public ExampleApplication(Environment environment, ExampleResource exampleResource) {
		this.environment = environment;
		this.exampleResource = exampleResource;
	}

	public void run() throws Exception {
		environment.jersey().register(exampleResource);
	}
}
