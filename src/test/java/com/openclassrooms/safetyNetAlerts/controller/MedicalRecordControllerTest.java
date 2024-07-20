package com.openclassrooms.safetyNetAlerts.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetyNetAlerts.model.MedicalRecord;
import com.openclassrooms.safetyNetAlerts.service.MedicalRecordService;

@WebMvcTest(controllers = MedicalRecordController.class)
public class MedicalRecordControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private MedicalRecordService medicalRecordService;

	List<String> medications = new ArrayList<String>();

	List<String> allergies = new ArrayList<String>();

	private String birthdate = "15/05/1979";

	private String medication1 = "doliprane: 500mg";
	private String allergie1 = "piqures";

	@Test
	public void testGetMedicalRecord() throws Exception {
		mockMvc.perform(get("/medicalRecord")).andExpect(status().isOk());
	}

	@Test
	public void testToCreateMedicalRecord() throws Exception {
		mockMvc.perform(post("/medicalRecord").contentType(MediaType.APPLICATION_JSON).content(
				"{\"firstName\": \"stephane\", \"lastName\": \"gastion\", \"birthdate\": \"15/06/1979\", \"medications\":[ \"doliprane:500mg\"], \"allergies\":[ \"piqures\"]}")
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());

		verify(medicalRecordService).createMedicalRecord(any(MedicalRecord.class));
	}

	@Test
	public void testToUpdateMediacalRecord() throws Exception {
		medications.add(medication1);
		allergies.add(allergie1);

		mockMvc.perform(
				put("/medicalRecord/{firstName} {lastName}", "steeve", "jobs").contentType(MediaType.APPLICATION_JSON)
						.content(jsonString(new MedicalRecord("steeve", "jobs", birthdate, medications, allergies)))
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

	}

	
	@Test  
	public void testToDeleteMedicalRecord() throws Exception {

		mockMvc.perform(delete("/medicalRecord/{firstName} {lastName}", "steeve", "jobs")).andExpect(status().isOk());

	}

	public static String jsonString(final MedicalRecord medicalRecord) {
		try {
			return new ObjectMapper().writeValueAsString(medicalRecord);
		} catch (Exception e) {

			throw new RuntimeException(e);
		}
	}
}
