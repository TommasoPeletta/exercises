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
	
	
	public void setPersonservice(PersonService ps) {
		personservice = ps;
	}

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
	public String addNewPerson(@PathParam("firstname") String firstname, 
			@PathParam("lastname") String lastname) {
		Person p = new Person(firstname, lastname);
		if(personservice.createPerson(p)) {
			return "You inserted "+ p.toString();	
		} else {
			return "Error. "+ p.toString() + " already exists";
		}
		
	}
	
	@GET
	@Path("/delete/{id}")
	@Produces("text/plain")
	public String deletePerson(@PathParam("id") String str_id) {
		long id = Long.parseLong(str_id);
		
		Optional<Person> popt = personservice.getById(id);
		if(popt.isEmpty()) {
			return "Error. There is no person with ID "+ id;
		}
		
		Person p = popt.get();
		
		try {
			personservice.deletePerson(p);
			return "Deleted person "+ p.toString();	
		} catch(IllegalArgumentException ex) {
			System.out.println(ex.toString());
			return "Some form of error occurred. Could not delete "+ p.toString();
		}
	}
}
