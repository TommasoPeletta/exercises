package ch.unige.pinfo.jpademo.rest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
	@Path("/")
	@Produces("text/plain")
	public String getAll() {
		List<Person> all = personservice.getAll();
		return all.stream().map(p -> p.toString()).collect(Collectors.joining("\n"));
	}
	
	
	@GET
	@Path("/new/{firstname}/{lastname}")
	@Produces("text/plain")
	public Response addNewPerson(@PathParam("firstname") String firstname, 
			@PathParam("lastname") String lastname) {
		Person p = new Person(firstname, lastname);
		if(personservice.createPerson(p)) {
			return Response.ok("You inserted "+ p.toString()).build();	
		} else {
			return Response.serverError().entity("Error. "+ p.toString() + " already exists").build();
		}
		
	}
	
	@GET
	@Path("/delete/{id}")
	@Produces("text/plain")
	public Response deletePerson(@PathParam("id") String str_id) {
		long id = Long.parseLong(str_id);
		
		Optional<Person> popt = personservice.getById(id);
		if(popt.isEmpty()) {
			return Response.serverError().entity("Error. There is no person with ID "+ id).build();
		}
		
		Person p = popt.get();
		
		try {
			personservice.deletePerson(p);
			return Response.ok("Deleted person "+ p.toString()).build();	
		} catch(IllegalArgumentException ex) {
			System.out.println(ex.toString());
			return Response.serverError().entity("Some form of error occurred. Could not delete "+ p.toString()).build();
		}
	}
}
