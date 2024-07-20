package com.openclassrooms.safetyNetAlerts.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.openclassrooms.safetyNetAlerts.model.MedicalRecord;

@Repository
public interface MedicalRecordDAO {

	public List<MedicalRecord> findAll();

	public MedicalRecord saveMedicalRecord(MedicalRecord medicalRecord);

	public MedicalRecord findByFirstNameAndLastName(String firstName, String lastName);

	public MedicalRecord updateMedicalRecord(MedicalRecord medicalRecordFound, MedicalRecord medicalRecordDetails);

	public void deleteMedicalRecord(String firstName, String lastName);

}
