package ch.unige.pinfo.jpademo.rest;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import ch.unige.pinfo.jpademo.model.Person;
import ch.unige.pinfo.jpademo.service.PersonService;

@ApplicationScoped
@Path("/person")
public class PersonEndpoint {

	@Inject
	private PersonService personservice;
	
	@GET
	@Produces("text/plain")
	public Response doGet() {
		return Response.ok("Hello from Thorntail!").build();
	}
	
	@GET
	@Path("/new/{firstname}/{lastname}")
	@Produces("text/plain")
	public Response addNewPerson(
			@PathParam("firstname") String firstname, 
			@PathParam("lastname") String lastname) {
		Person p = new Person(firstname, lastname);
		if(personservice.createPerson(p)) {
			return Response.ok("You inserted "+ firstname + " " + lastname).build();	
		} else {
			return Response.ok("Error. "+ firstname + " " + lastname+ " already exists").build();
		}
		
	}
}
