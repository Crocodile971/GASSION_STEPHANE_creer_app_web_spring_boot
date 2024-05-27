package com.openclassrooms.safetyNetAlerts.dao;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.openclassrooms.safetyNetAlerts.config.JsonDataLoader;
import com.openclassrooms.safetyNetAlerts.model.Person;

@Repository
public class PersonDAOImpl implements PersonDAO {

	private static final Logger logger = LogManager.getLogger(PersonDAOImpl.class);

	JsonDataLoader loader = new JsonDataLoader();
	List<Person> persons = loader.loadPersonData("data.json");

	@Override
	public List<Person> findAll() {
		logger.info("Initialize all persons data file");

		return persons;
	}

	@Override
	public Person savePerson(Person person) {

		try {
			if (!persons.contains(person)) {
				logger.debug("To debug add person {} ", person.toString());
				persons.add(person);
				logger.info("successfully save person {} ", person);
			} else {
				persons.removeIf(removePerson -> removePerson.getFirstName().equals(person.getFirstName())
						&& removePerson.getLastName().equals(person.getLastName()));
				persons.add(person);

				logger.info("successfully save after remove person. {}", person);

			}
		} catch (NullPointerException e) {
			logger.error("receive null pointer exception to save person ", e);
		} catch (Exception e) {
			logger.error("receive exception to save person ", e);
			e.printStackTrace();
		}
		return person;

	}

	@Override
	public Person findByFirstNameAndLastName(String firstName, String lastName) {

		try {
			logger.trace("About filtering a person by first {} and last name {} ", firstName, lastName);

			Person person = persons.stream()
					.filter(personFilterBy -> personFilterBy.getFirstName().equalsIgnoreCase(firstName)
							&& personFilterBy.getLastName().equalsIgnoreCase(lastName)).findFirst().orElse(null);

			logger.info("We find {} {} successfully {} ", firstName, lastName, person);
			
			return person;     

		} catch (NullPointerException e) {
			logger.error("failed find By First Name And LastName ", e);
		}
		return null;
	}

	@Override
	public Person updatePerson(Person personFound, Person personDetails) {

		logger.debug("Debug to updating person");

		if (personFound.getAddress() != null) {
			personFound.setAddress(personDetails.getAddress());
		}

		if (personFound.getCity() != null) {
			personFound.setCity(personDetails.getCity());
		}

		if (personFound.getZip() != null) {
			personFound.setZip(personDetails.getZip());
		}

		if (personFound.getPhone() != null) {
			personFound.setPhone(personDetails.getPhone());
		}

		if (personFound.getEmail() != null) {
			personFound.setEmail(personDetails.getEmail());
		}

		
		logger.info("Updating person successfully : {} {} . ", personFound.getFirstName(), personFound.getLastName());
		return personFound;
	}

	@Override
	public void deletePerson(String firstName, String lastName) {

		logger.debug("Debug remove: firstName: {} and lastName: {} ", firstName, lastName);
		persons.removeIf(removePerson -> removePerson.getFirstName().equals(firstName)
				&& removePerson.getLastName().equals(lastName));

		logger.info("Deleting {} {} successfully ", firstName, lastName);
	}

}
