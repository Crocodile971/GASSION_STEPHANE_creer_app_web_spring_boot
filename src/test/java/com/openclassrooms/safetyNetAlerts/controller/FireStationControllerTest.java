package com.openclassrooms.safetyNetAlerts.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetyNetAlerts.model.FireStation;
import com.openclassrooms.safetyNetAlerts.service.FireStationService;

@WebMvcTest(controllers = FireStationController.class)
public class FireStationControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private FireStationService fireStationService;

	@Test
	public void testGetFireStations() throws Exception {
		mockMvc.perform(get("/fireStation")).andExpect(status().isOk());
	}

	@Test
	public void testCreateFireStation() throws Exception {
		mockMvc.perform(post("/fireStation").contentType(MediaType.APPLICATION_JSON)
				.content("{\"address\": \"125 rue des trolls\", \"station\": \"5\"}")
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());

	}

	@Test
	public void testUpdateFireStation() throws Exception {
		mockMvc.perform(put("/fireStation/{address} {station}", "160 court deval", "9").contentType(MediaType.APPLICATION_JSON)
				.content(jsonString(new FireStation("25 Culvertion", "6"))).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

	}

	@Test
	public void testDeleteFireStation() throws Exception {

		mockMvc.perform(delete("/fireStation/{address} {station}", "160 court deval", "9")).andExpect(status().isOk());
	}

	public static String jsonString(final FireStation fireStation) {
		try {
			return new ObjectMapper().writeValueAsString(fireStation);
		} catch (Exception e) {

			throw new RuntimeException(e);
		}
	}

}
