package com.openclassrooms.safetyNetAlerts.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetyNetAlerts.model.Person;
import com.openclassrooms.safetyNetAlerts.service.PersonService;

@WebMvcTest(controllers = PersonController.class)
public class PersonControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PersonService personService;

	@Test
	public void testGetPersons() throws Exception {
		mockMvc.perform(get("/person")).andExpect(status().isOk());
	}

	@Test
	public void testCreatePerson() throws Exception {
		mockMvc.perform(post("/person").contentType(MediaType.APPLICATION_JSON).content(
				"{\"firstName\": \"stephane\", \"lastName\": \"gastion\", \"address\": \"15 rue des trolls\", \"city\": \"culver\", \"zip\": \"95700\", \"phone\": \"123-456-789\", \"email\": \"job@email.com\"}")
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());

		verify(personService).createPerson(any(Person.class));
	}

	@Test
	public void testUpdatePerson() throws Exception {

		mockMvc.perform(put("/person/{firstName}{lastName}", "steeve", "jobs").contentType(MediaType.APPLICATION_JSON)
				.content(jsonString(new Person("steeve", "jobs", "15 rue des trolls", "culver", "95700", "123-456-789",

						"job@email.com")))
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}

	@Test
	public void testDeletePerson() throws Exception {

		mockMvc.perform(delete("/person/{firstName}{lastName}", "steeve", "jobs")).andExpect(status().isOk());
	}

	public static String jsonString(final Person person) {
		try {
			return new ObjectMapper().writeValueAsString(person);
		} catch (Exception e) {

			throw new RuntimeException(e);
		}
	}
}
