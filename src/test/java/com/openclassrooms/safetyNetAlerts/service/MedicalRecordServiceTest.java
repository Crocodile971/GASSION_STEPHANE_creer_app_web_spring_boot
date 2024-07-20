package com.openclassrooms.safetyNetAlerts.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.openclassrooms.safetyNetAlerts.dao.MedicalRecordDAO;
import com.openclassrooms.safetyNetAlerts.model.MedicalRecord;

@ExtendWith(MockitoExtension.class)
public class MedicalRecordServiceTest {

	@Mock
	private MedicalRecordDAO medicalRecordDao;
	
	@InjectMocks
	private MedicalRecordService medicalRecordService; 
	
	private MedicalRecord medicalRecord;
	
	List<String> medications = new ArrayList<String>();

	List<String> allergies = new ArrayList<String>();

	private String birthdate = "15/05/1979";

	private String medication1 = "doliprane: 500mg";
	private String allergie1 = "piqures";
	
	
	@BeforeEach
	public void setup() {
		medications.add(medication1);
		allergies.add(allergie1);
		medicalRecord = new MedicalRecord("Stephane", "GASTON", birthdate, medications, allergies);
	}
	 
	@DisplayName("Test for get all medical record method")
	@Test
	public void testGetAllMedicalRecord() {

		// given
		MedicalRecord medicalRecord1 = new MedicalRecord("Stephane", "GASTON", birthdate, medications, allergies);

		when(medicalRecordDao.findAll()).thenReturn(List.of(medicalRecord, medicalRecord1));

		// when
		List<MedicalRecord> medicalRecordList = medicalRecordService.getMedicalRecord();

		// then
		assertThat(medicalRecordList).isNotNull(); 
	}
	
	@DisplayName("Test for create medical record method")
	@Test
	public void testToCreatemedicalRecord() {
		// given
		when(medicalRecordDao.saveMedicalRecord(medicalRecord)).thenReturn(medicalRecord);

		// when
		MedicalRecord saveMedicalrecord = medicalRecordService.createMedicalRecord(medicalRecord);

		// then
		assertThat(saveMedicalrecord).isNotNull();
	}
	
	
	@DisplayName("Test for update medical record method")
	@Test
	public void testToUpdateMedicalRecord() {
		// given
		when(medicalRecordDao.findByFirstNameAndLastName("Stephane", "GASTON")).thenReturn(medicalRecord);
		when(medicalRecordDao.saveMedicalRecord(medicalRecord)).thenReturn(medicalRecord);
		medicalRecord.setMedications(medications);
		medicalRecord.setAllergies(allergies);

		// when
		MedicalRecord updateMedicalRecord = medicalRecordService.updateMedicalRecord("Stephane", "GASTON", medicalRecord);

		// then
		assertThat(updateMedicalRecord.getMedications()).isEqualTo(medications);
		assertThat(updateMedicalRecord.getAllergies()).isEqualTo(allergies);
	}
	
	@DisplayName("Test for delete medical record method")
	@Test
	public void testToDeleteMedicalRecord() { 
		// given
		String firstName = medicalRecord.getFirstName();
		String lastName = medicalRecord.getLastName();

		medicalRecordDao.deleteMedicalRecord(firstName, lastName);

		// when
		medicalRecordService.deleteMedicalRecord(firstName, lastName);

		// then
		verify(medicalRecordDao, times(2)).deleteMedicalRecord(firstName, lastName);
	}
}
