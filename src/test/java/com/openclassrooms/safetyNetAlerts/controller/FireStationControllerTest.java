package com.openclassrooms.safetyNetAlerts.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.openclassrooms.safetyNetAlerts.service.FireStationService;

@WebMvcTest(controllers = FireStationController.class)
public class FireStationControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private FireStationService fireStationService;
	
	@Test
	public void testGetFireStations() throws Exception {
		mockMvc.perform(get("/fireStation"))
		.andExpect(status().isOk());
	}

}
