package com.openclassrooms.safetyNetAlerts.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.safetyNetAlerts.dao.PersonDAO;
import com.openclassrooms.safetyNetAlerts.model.Person;

import lombok.Data;

@Data
@Service
public class PersonService {

	private static final Logger logger = LogManager.getLogger(PersonService.class);
	@Autowired
	private PersonDAO personDao;

	/**
	 * Method to get all persons
	 * 
	 * @return all persons of the list.
	 */
	public List<Person> getPersons() {
		logger.info("List of all persons");
		return personDao.findAll();
	}

	/**
	 * method for creating a person
	 * 
	 * @param person Object input parameter
	 * @return The creation of a person
	 */
	public Person createPerson(Person person) {

		logger.debug("To debug the creating of person {} ", person.toString());

		personDao.savePerson(person);

		logger.info("Successfully save person {} ", person.toString());
		return person;
	}

	/**
	 * method for updating a person with a unique first and last name identifier
	 * 
	 * @param firstName     input parameter
	 * @param lastName      input parameter
	 * @param personDetails input parameter
	 * @return A person's modification
	 */
	public Person updatePerson(String firstName, String lastName, Person personDetails) {

		logger.debug("Update person debug {} {}", firstName, lastName);
		Person updatePerson = personDao.findByFirstNameAndLastName(firstName, lastName);

		if (updatePerson != null) {

			personDao.updatePerson(updatePerson, personDetails);

			logger.info("successfully updated person {} {} {} ", firstName, lastName, personDetails.toString());

			return personDao.savePerson(updatePerson);
		} else {

			logger.error("Not person to update: {} {} .", firstName, lastName);
			return null;
		}

	}

	/**
	 * method for deleting a person
	 * 
	 * @param firstName input parameter
	 * @param lastName  input parameter
	 */
	public void deletePerson(String firstName, String lastName) {

		logger.debug("Deleting for firstName {} and lastName {} ", firstName, lastName);
		personDao.deletePerson(firstName, lastName);
	}

}
