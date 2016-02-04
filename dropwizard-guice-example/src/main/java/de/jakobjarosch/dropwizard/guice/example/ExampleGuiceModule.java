package de.jakobjarosch.dropwizard.guice.example;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.google.inject.AbstractModule;
import com.google.inject.BindingAnnotation;

public class ExampleGuiceModule extends AbstractModule {


	@BindingAnnotation
	@Target({ ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD })
	@Retention(RetentionPolicy.RUNTIME)
	public @interface TrailingPunctation {
	}
	
	@Override
	protected void configure() {
		bind(String.class).annotatedWith(TrailingPunctation.class).toInstance("!?");
	}

}
