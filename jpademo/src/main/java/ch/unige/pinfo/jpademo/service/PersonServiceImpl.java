package ch.unige.pinfo.jpademo.service;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import ch.unige.pinfo.jpademo.model.Person;

@ApplicationScoped
@Transactional
@Default
public class PersonServiceImpl implements PersonService {

	@PersistenceContext(name="InmemoryPU")
	EntityManager em;

	@Override
	public List<Person> getAll() {
		List<Person> people = em.createQuery("SELECT a FROM Person a", Person.class).getResultList();
		return people;

	}
	
	@Override
	public boolean createPerson(Person p) {
		Optional<Person> existing = this.getByNames(p.getFirstname(), p.getLastname());
		if(!existing.isPresent()) {
			em.persist(p);
			return true;
		}
		return false;
	}

	@Override
	public Optional<Person> getByNames(String first, String last) {
		List<Person> people = em.createQuery("SELECT a FROM Person a WHERE LOWER(a.firstname) = LOWER('"+first+"') AND LOWER(a.lastname) = LOWER('"+last+"')", Person.class).getResultList();
		if(people.size() > 0) {
			return Optional.of(people.get(0));
		}
		
		return Optional.empty();
	}

	@Override
	public Optional<Person> getById(long id) {
		List<Person> people = em.createQuery("SELECT a FROM Person a WHERE a.id = "+id, Person.class).getResultList();
		if(people.size() > 0) {
			return Optional.of(people.get(0));
		}
		return Optional.empty();
	}

	@Override
	public void deletePerson(Person person) {
		// gotta merge first to get the person into the manager context
		// https://stackoverflow.com/a/17027553/3793083
		em.remove(em.contains(person) ? person : em.merge(person));
	}
	
}
