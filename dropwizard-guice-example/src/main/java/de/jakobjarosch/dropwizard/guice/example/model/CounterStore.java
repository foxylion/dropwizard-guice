package de.jakobjarosch.dropwizard.guice.example.model;

import java.util.concurrent.atomic.AtomicLong;

import javax.inject.Singleton;

@Singleton
public class CounterStore {

	private AtomicLong counter = new AtomicLong();
	
	public long get() {
		return counter.incrementAndGet();
	}
	
}
