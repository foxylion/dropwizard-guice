package de.jakobjarosch.dropwizard.guice.example.resource;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import de.jakobjarosch.dropwizard.guice.example.model.RequestScopeModel;
import de.jakobjarosch.dropwizard.guice.example.model.ResponseModel;
import de.jakobjarosch.dropwizard.guice.example.model.ResponseModelFactory;

@Path("/example")
@Produces(MediaType.APPLICATION_JSON)
public class ExampleResource {

	private final Provider<RequestScopeModel> requestScoped;
	private final ResponseModelFactory responseBuilder;

	@Inject
	public ExampleResource(ResponseModelFactory responseBuilder, Provider<RequestScopeModel> requestScoped) {
		this.responseBuilder = responseBuilder;
		this.requestScoped = requestScoped;
	}

	@GET
	public Response example(@Context HttpServletRequest requestContext) {
		requestScoped.get().setIpAddress(requestContext.getRemoteAddr());

		ResponseModel response = responseBuilder.create();
		return Response.ok().entity(response).build();
	}
}
