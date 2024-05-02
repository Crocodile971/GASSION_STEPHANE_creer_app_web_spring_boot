package com.openclassrooms.safetyNetAlerts.dao;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.openclassrooms.safetyNetAlerts.config.JsonDataLoader;
import com.openclassrooms.safetyNetAlerts.model.Person;

@Repository
public class PersonDAOImpl implements PersonDAO {

	private static final Logger logger = LogManager.getLogger("PersonDAOImpl");

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
			if(!persons.contains(person)) {
				persons.add(person);
			}else {
				throw new Exception("Failed to save person");
			}
		}catch(NullPointerException e) {
			logger.error("receive null pointer exception ", e);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return person;

	}

	@Override
	public void deletePerson(String firstName, String lastName) {
		// TODO Auto-generated method stub

	}

}
