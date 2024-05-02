package com.openclassrooms.safetyNetAlerts.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

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
		mockMvc.perform(get("/person"))
		.andExpect(status().isOk());
	}
	
	@Test
	public void testCreatePerson() throws Exception {
		mockMvc.perform(post("/person")
			.contentType(MediaType.APPLICATION_JSON)
			.content("{\"firstName\": \"stephane\", \"lastName\": \"gastion\"}"))
		.andExpect(status().isCreated());
		
		verify(personService).createPerson(any(Person.class));
	}
	
}
