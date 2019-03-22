package ch.unige.pinfo.jpademo.service;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import ch.unige.pinfo.jpademo.model.Person;

@ApplicationScoped
@Transactional
public class PersonServiceImpl implements PersonService {

	@PersistenceContext
	EntityManager em;
	
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
	public List<Person> getAll() {
		List<Person> people = em.createQuery("SELECT a FROM Person a", Person.class).getResultList();
		return people;

	}

	@Override
	public Optional<Person> getByNames(String first, String last) {
		List<Person> people = em.createQuery("SELECT a FROM Person a WHERE LOWER(a.firstname) = LOWER('"+first+"') AND LOWER(a.lastname) = LOWER('"+last+"')", Person.class).getResultList();
		if(people.size() > 0) {
			return Optional.of(people.get(0));
		}
		
		return Optional.empty();
	}
	
}
