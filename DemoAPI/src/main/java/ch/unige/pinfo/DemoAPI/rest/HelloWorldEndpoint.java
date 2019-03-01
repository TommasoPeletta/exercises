package ch.unige.pinfo.DemoAPI.rest;


import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;


@Path("/hello")
public class HelloWorldEndpoint {

	@GET
	@Produces("text/html")
	public String doGet() {
		return "<h1>hello from Stefan</h1>";
//		return Response.ok("Hello from Thorntail!").build();
	}
	
	
	@GET
	@Produces("text/html")
	@Path("/{name}")
	public String hello_name(@PathParam("name") String name) {
		return "Hello " + name + "! Nice to see you!";
	}
	
	@GET
	@Produces("text/html")
	@Path("/plusage/{name}")
	public String hello_name_age(@PathParam("name") String name,
			@DefaultValue("25") @QueryParam("age") int age) {
		return "Hello " + name + "! I see you're " + age + " years old.";
	}
}
