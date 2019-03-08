package ch.unige.pinfo.cdibakery.rest;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import ch.unige.pinfo.cdibakery.model.bakery.IBakery;

@ApplicationScoped
@Path("/")
public class HelloWorldEndpoint {

	@GET
	@Produces("text/plain")
	public Response doGet() {
		return Response.ok("Hello from Thorntail!").build();
	}
	
	@Inject
	IBakery bakery;
	
	@GET
	@Path("/bake")
	@Produces("text/plain")
	public String bake() {
//		IBakery bakery = new FastBakery();
		return this.bakery.bake();
	}
}
