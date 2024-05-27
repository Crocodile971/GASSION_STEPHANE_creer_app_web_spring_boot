package com.openclassrooms.safetyNetAlerts.serviceIT;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.openclassrooms.safetyNetAlerts.dao.PersonDAO;
import com.openclassrooms.safetyNetAlerts.model.Person;
import com.openclassrooms.safetyNetAlerts.service.PersonService;

@SpringBootTest
public class PersonServiceIT {

	@Autowired
	private PersonService personService;

	@MockBean
	private PersonDAO personDao;

	@BeforeEach
	public void setup() {

		Person person = new Person("Stephane", "GASTON", "25 rue des follies bergeres", "Cuvillier", "91000",
				"987-567-258", "stephane@gmail.com");
	}

	@DisplayName("IT test for getPersons method")
	@Test
	public void testGetPersons() {

		// given

		// when
		List<Person> personList = personService.getPersons();

		// then
		assertThat(personList).isNotNull();

	}

	@DisplayName("IT test for createPerson method")
	@Test
	public void testToCreatePerson() {
		// given
		Person person = new Person("Stephanis", "GASTIONIS", "150 rue des follies", "Bliver", "95000", "123-452-214",
				"silvers@gmail.com");

		// when
		Person savePerson = personService.createPerson(person);

		// then
		assertThat(savePerson).isNotNull();
	}

	@DisplayName("IT test for updatePerson method")
	@Test
	public void testToUpdatePerson() {
		// given

		Person existingPerson = new Person("Stephane", "GASTON", "25 rue des follies bergeres", "Cuvillier", "91000",
				"987-567-258", "stephane@gmail.com");

		Person updatingPerson = new Person("Stephane", "GASTON", "25 rue des follies bergeres", "Cuvillier", "91200",
				"987-567-258", "stgaston@gmail.com");

		when(personDao.findByFirstNameAndLastName("Stephane", "GASTON")).thenReturn(existingPerson);
		when(personDao.savePerson(any(Person.class))).thenReturn(updatingPerson);

		// when
		Person resultUpdateperson = personService.updatePerson(existingPerson.getFirstName(),
				existingPerson.getLastName(), updatingPerson);

		// then
		assertThat(resultUpdateperson.getEmail()).isEqualTo("stgaston@gmail.com");
		assertThat(resultUpdateperson.getZip()).isEqualTo("91200");
	}

	@DisplayName("IT test for deletePerson method")
	@Test
	public void testToDeletePerson() {
		// given
		Person person = new Person("Steeven", "BAKO", "25 rue des follies", "Guliver", "95000", "123-452-214",
				"sgs@gmail.com");
		String firstName = person.getFirstName();
		String lastName = person.getLastName();

		personDao.deletePerson(firstName, lastName);

		// when
		personService.deletePerson(firstName, lastName);

		// then
		verify(personDao, times(2)).deletePerson(firstName, lastName);
	}
}
