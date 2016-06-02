package de.jakobjarosch.dropwizard.guice;

import java.util.EnumSet;
import java.util.function.Consumer;

import javax.servlet.DispatcherType;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Module;
import com.google.inject.servlet.GuiceFilter;

import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class GuiceBootstrap<T extends Configuration> extends Application<T> {

	private final Class<T> configClass;
	private final Class<? extends GuiceApplication> applicationClass;
	private final Module mainModule;
	private final Consumer<Bootstrap<T>> initializer;

	public GuiceBootstrap(Class<T> configClass, Class<? extends GuiceApplication> applicationClass) {
		this(configClass, applicationClass, null);
	}

	public GuiceBootstrap(Class<T> configClass, Class<? extends GuiceApplication> applicationClass, Module mainModule) {
		this(configClass, applicationClass, mainModule, null);
	}

	public GuiceBootstrap(Class<T> configClass, Class<? extends GuiceApplication> applicationClass, Module mainModule, Consumer<Bootstrap<T>> initializer) {
		this.configClass = configClass;
		this.applicationClass = applicationClass;
		this.mainModule = mainModule;
		this.initializer = initializer;
	}
	
	@Override
	public void initialize(Bootstrap<T> bootstrap) {
		if(initializer != null) {
			initializer.accept(bootstrap);
		}
	}
	
	@Override
	public void run(final T config, final Environment environment) throws Exception {
		Module bootstrapModule = new AbstractModule() {
			@Override
			protected void configure() {
				install(new DropwizardModule());
				if (mainModule != null)
					install(mainModule);

				bind(Configuration.class).toInstance(config);
				bind(configClass).toInstance(config);
				bind(Environment.class).toInstance(environment);
			}
		};

		environment.servlets() //
				.addFilter("guice-request-scope", new GuiceFilter()) //
				.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");

		Guice.createInjector(bootstrapModule).getInstance(applicationClass).run();
	}
}
