package com.openclassrooms.safetyNetAlerts.controllerIT;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetyNetAlerts.model.Person;

@SpringBootTest
@AutoConfigureMockMvc
public class PersonControllerIT {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testgetPersons() throws Exception {
		mockMvc.perform(get("/person")).andExpect(status().isOk()).andExpect(jsonPath("$[0].firstName").isNotEmpty());
	}

	@Test
	public void testCreatePerson() throws Exception {
		mockMvc.perform(post("/person").contentType(MediaType.APPLICATION_JSON).content(
				"{\"firstName\": \"stephane\", \"lastName\": \"gastion\", \"address\": \"15 rue des trolls\", \"city\": \"culver\", \"zip\": \"95700\", \"phone\": \"123-456-789\", \"email\": \"job@email.com\"}")
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
				.andExpect(jsonPath("$.firstName").value("stephane"))
				.andExpect(jsonPath("$.lastName").value("gastion"));

	}

	@Test
	public void testUpdatePerson() throws Exception {

		mockMvc.perform(put("/person/{firstName} {lastName}", "John", "Boyd").contentType(MediaType.APPLICATION_JSON)
				.content(jsonString(new Person("John", "Boyd", "150 rue des trolls", "culverli", "95700", "123-456-789",

						"job@email.com")))
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.address").value("150 rue des trolls"))
				.andExpect(jsonPath("$.city").value("culverli"));

	}

	@Test
	public void tesDeletePerson() throws Exception {
		mockMvc.perform(delete("/person/{firstName} {lastName}", "Johnny", "Boy")).andExpect(status().isOk());

	}

	public static String jsonString(final Person person) {
		try {
			return new ObjectMapper().writeValueAsString(person);
		} catch (Exception e) {

			throw new RuntimeException(e);
		}
	}

}
