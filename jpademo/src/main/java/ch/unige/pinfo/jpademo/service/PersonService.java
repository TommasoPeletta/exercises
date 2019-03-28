package ch.unige.pinfo.jpademo.service;

import java.util.List;
import java.util.Optional;

import ch.unige.pinfo.jpademo.model.Person;

public interface PersonService {
	public List<Person> getAll();

	public boolean createPerson(Person p);

	public Optional<Person> getByNames(String first, String last);

	public Optional<Person> getById(long id);

	public void deletePerson(Person person);

}
