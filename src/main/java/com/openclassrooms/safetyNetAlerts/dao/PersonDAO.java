package com.openclassrooms.safetyNetAlerts.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.openclassrooms.safetyNetAlerts.model.Person;

@Repository
public interface PersonDAO {

	public List<Person> findAll();
	
	public Person savePerson(Person person);
	
	public Person findByFirstNameAndLastName(String firstName, String lastName);
	
	public Person updatePerson(Person personFound, Person personDetails);

	public void deletePerson(String firstName, String lastName);
}
 