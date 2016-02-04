# Guice intergration for Dropwizard

A **minimalistic integration** between the dependency injection framework **[Guice](https://github.com/google/guice)** and the microservice framework **[Dropwizard](http://www.dropwizard.io/)**.

## How to use?

This integration aims to introduce minimal effort to get started

### Configure the Maven Dependencies

```xml
<dependency>
    <groupId>de.jakobjarosch.dropwizard</groupId>
    <artifactId>dropwizard-guice</artifactId>
    <version>0.9.2-1</version>
</dependency>
```

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
