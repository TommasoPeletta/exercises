package ch.unige.pinfo.jpademo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PERSONS")
public class Person implements Serializable {

	private static final long serialVersionUID = 8670524760980617633L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="PERSON_ID")
	long id;
	
	@Column(name="FIRSTNAME")
	String firstname;
	
	@Column(name="LASTNAME")
	String lastname;
	
	public Person() {}
	
	public Person(String first, String last) {
		this.firstname = first;
		this.lastname = last;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	
	
}
