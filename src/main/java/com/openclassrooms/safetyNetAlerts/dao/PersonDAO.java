package com.openclassrooms.safetyNetAlerts.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.openclassrooms.safetyNetAlerts.model.Person;

@Repository
public interface PersonDAO {

	public List<Person> findAll();
	
	Person savePerson(Person person);
	
	public void deletePerson(String firstName, String lastName);

}
