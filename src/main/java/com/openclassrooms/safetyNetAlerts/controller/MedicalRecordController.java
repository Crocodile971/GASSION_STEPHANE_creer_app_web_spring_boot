package com.openclassrooms.safetyNetAlerts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.safetyNetAlerts.model.MedicalRecord;
import com.openclassrooms.safetyNetAlerts.model.Person;
import com.openclassrooms.safetyNetAlerts.service.MedicalRecordService;

@RestController
public class MedicalRecordController {

	@Autowired
	private MedicalRecordService medicalRecordService;

	@GetMapping("/medicalRecord")
	public List<MedicalRecord> getAllMedicalRecord() {
		return medicalRecordService.getMedicalRecord();
	}

	@PostMapping("/medicalRecord")
	public ResponseEntity<MedicalRecord> createMedicalRecord(@RequestBody MedicalRecord medicalRecord) {

		MedicalRecord saveMedicalRecord = medicalRecordService.createMedicalRecord(medicalRecord);

		return new ResponseEntity<>(saveMedicalRecord, HttpStatus.CREATED);

	}

	@PutMapping("/medicalRecord/{firstName} {lastName}")
	public ResponseEntity<MedicalRecord> updateMedicalRecord(@PathVariable(name = "firstName") String firstName,
			@PathVariable(name = "lastName") String lastName, @RequestBody MedicalRecord medicalRecordDetails) {

		if (medicalRecordDetails != null) { 

			MedicalRecord medicalRecordUpdate = medicalRecordService.updateMedicalRecord(firstName, lastName, medicalRecordDetails);

			return ResponseEntity.ok(medicalRecordUpdate);
		} else {
			return ResponseEntity.notFound().build();
		} 
	}
	  
	@DeleteMapping("/medicalRecord/{firstName} {lastName}")
	public ResponseEntity<String> deleteMedicalRecord(@PathVariable(name = "firstName") String firstName,
			@PathVariable(name = "lastName") String lastName) {

		medicalRecordService.deleteMedicalRecord(firstName, lastName);

		return ResponseEntity.ok("Medical record deleted successfully!.");

	}
}
