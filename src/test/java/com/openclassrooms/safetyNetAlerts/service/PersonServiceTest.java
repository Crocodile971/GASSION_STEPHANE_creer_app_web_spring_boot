package com.openclassrooms.safetyNetAlerts.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.openclassrooms.safetyNetAlerts.dao.PersonDAO;
import com.openclassrooms.safetyNetAlerts.dao.PersonDAOImpl;
import com.openclassrooms.safetyNetAlerts.model.Person;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

	@Mock
	private PersonDAO personDao;

	@Mock
	private PersonDAOImpl personDaoImpl;

	@InjectMocks
	private PersonService personService;

	private Person person;

	@BeforeEach
	public void setup() {

		person = new Person("Stephane", "GASTON", "25 rue des follies bergeres", "Cuvillier", "91000", "987-567-258",
				"stephane@gmail.com");
	}

	@DisplayName("Test for getAllPersons method")
	@Test
	public void testGetAllPersons() {

		// given
		Person person1 = new Person("Steph", "GAS", "15 rue des follies", "Cliver", "95000", "123-452-214",
				"sgs@gmail.com");

		when(personDao.findAll()).thenReturn(List.of(person, person1));

		// when
		List<Person> personList = personService.getPersons();

		// then
		assertThat(personList).isNotNull();
	}

	@DisplayName("Test for createPerson method")
	@Test
	public void testToCreatePerson() {
		// given
		when(personDao.savePerson(person)).thenReturn(person);

		// when
		Person savePerson = personService.createPerson(person);

		// then
		assertThat(savePerson).isNotNull();
	}

	@DisplayName("Test for updatePerson method")
	@Test
	public void testToUpdatePerson() {
		// given
		when(personDao.findByFirstNameAndLastName("Stephane", "GASTON")).thenReturn(person);
		when(personDao.savePerson(person)).thenReturn(person);
		person.setEmail("stgaston@gmail.com");
		person.setZip("91200");

		// when
		Person updatePerson = personService.updatePerson("Stephane", "GASTON", person);

		// then
		assertThat(updatePerson.getEmail()).isEqualTo("stgaston@gmail.com");
		assertThat(updatePerson.getZip()).isEqualTo("91200");
	}

	@DisplayName("Test for deletePerson method")
	@Test
	public void testToDeletePerson() {
		// given
		String firstName = person.getFirstName();
		String lastName = person.getLastName();

		personDao.deletePerson(firstName, lastName);

		// when
		personService.deletePerson(firstName, lastName);

		// then
		verify(personDao, times(2)).deletePerson(firstName, lastName);
	}

}
