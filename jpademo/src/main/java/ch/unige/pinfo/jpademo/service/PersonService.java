package ch.unige.pinfo.jpademo.service;

import java.util.List;
import java.util.Optional;

import ch.unige.pinfo.jpademo.model.Person;

public interface PersonService {
	public boolean createPerson(Person p);
	
	public List<Person> getAll();
	
	public Optional<Person> getByNames(String first,
			String last);
}
