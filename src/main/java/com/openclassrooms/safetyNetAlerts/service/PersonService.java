package com.openclassrooms.safetyNetAlerts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.safetyNetAlerts.dao.PersonDAO;
import com.openclassrooms.safetyNetAlerts.model.Person;

import lombok.Data;

@Data
@Service
public class PersonService {

	@Autowired
	private PersonDAO personDao;

	public List<Person> getPersons() {
		return personDao.findAll();
	}
	
	public Person createPerson(Person person) {
		personDao.savePerson(person);
		return person;
	}

}
