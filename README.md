# Guice intergration for Dropwizard

[![Build Status](https://img.shields.io/travis/foxylion/dropwizard-guice/master.svg?style=flat-square)](https://travis-ci.org/foxylion/dropwizard-guice)
[![Maven Version](https://img.shields.io/maven-central/v/de.jakobjarosch.dropwizard/dropwizard-guice.svg?style=flat-square)](https://search.maven.org/#search%7Cga%7C1%7Cg%3A%22de.jakobjarosch.dropwizard%22%20AND%20a%3A%22dropwizard-guice%22)
![License](https://img.shields.io/badge/license-Apache%202.0-blue.svg?style=flat-square)
![Maintenance](https://img.shields.io/maintenance/yes/2016.svg?style=flat-square)

A **minimalistic integration** between the dependency injection framework **[Guice](https://github.com/google/guice)** and the microservice framework **[Dropwizard](http://www.dropwizard.io/)**.

## How to use?

This integration aims to introduce minimal effort to get started

### Configure the Maven Dependencies

```xml
<dependency>
    <groupId>de.jakobjarosch.dropwizard</groupId>
    <artifactId>dropwizard-guice</artifactId>
    <version>{{ current-version }</version>
</dependency>
```

The current version can be found [here](https://search.maven.org/#search%7Cga%7C1%7Cg%3A%22de.jakobjarosch.dropwizard%22%20AND%20a%3A%22dropwizard-guice%22).

### Create your Application

To get started with Dropwizard and Guice you only need a single class which invokes the bootstrapping.

```java
public class MyApplication implements GuiceApplication {
	public static void main(String[] args) throws Exception {
		new GuiceBootstrap<MyConfiguration>(MyConfiguration.class,
                                            MyApplication.class,
                                            new ExampleGuiceModule()) {
		}.run(args);
	}

	private final Environment environment;
	private final MyResource myResource;

	@Inject
	public MyApplication(Environment environment, MyResource myResource) {
		this.environment = environment;
		this.myResource = myResource;
	}

	public void run() throws Exception {
		environment.jersey().register(myResource);
	}
}
```

It is important, that the ``main`` method does invoke the ``GuiceBootstrap`` with an anonymous implementation like in the example. This is required due to the generic type paremeter resolution for Dropwizard.

**More features can be found in the [example](https://github.com/foxylion/dropwizard-guice/tree/master/dropwizard-guice-example/).**
