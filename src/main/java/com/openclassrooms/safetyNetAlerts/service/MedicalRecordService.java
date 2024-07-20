package com.openclassrooms.safetyNetAlerts.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.safetyNetAlerts.dao.MedicalRecordDAO;
import com.openclassrooms.safetyNetAlerts.model.MedicalRecord;

@Service
public class MedicalRecordService {

	private static final Logger logger = LogManager.getLogger(MedicalRecordService.class);

	@Autowired
	private MedicalRecordDAO medicalRecordDao;

	
	/**
	 * Method to get all medical record
	 * 
	 * @return all medical record of the list
	 */
	public List<MedicalRecord> getMedicalRecord() {
		logger.info("List of all medical record");
		return medicalRecordDao.findAll(); 
	}

	
	/**
	 * method for creating a medical record
	 * 
	 * @param medicalRecord Object input parameter
	 * @return the creation of a medical record
	 */
	public MedicalRecord createMedicalRecord(MedicalRecord medicalRecord) {

		logger.debug("To debug the creating of medical record {} ", medicalRecord.toString());

		medicalRecordDao.saveMedicalRecord(medicalRecord);

		logger.info("Successfully save medical record {} ", medicalRecord.toString());
		
		return medicalRecord;

	}

	
	/**
	 * method for updating a person with a unique first and last name identifier
	 * 
	 * @param firstName input parameter
	 * @param lastName input parameter
	 * @param medicalRecordDetails input parameter
	 * @return a medical record modification
	 */
	public MedicalRecord updateMedicalRecord(String firstName, String lastName, MedicalRecord medicalRecordDetails) {

		logger.debug("Update medical record debug {} {}", firstName, lastName);
		MedicalRecord updateMedicalRecord = medicalRecordDao.findByFirstNameAndLastName(firstName, lastName);

		if (updateMedicalRecord != null) {

			medicalRecordDao.updateMedicalRecord(updateMedicalRecord, medicalRecordDetails);

			logger.info("successfully updated medical record {} {} {} ", firstName, lastName, medicalRecordDetails.toString());

			return medicalRecordDao.saveMedicalRecord(updateMedicalRecord);
		} else {

			logger.error("Not medical record to update: {} {} .", firstName, lastName);
			return null;
		}
	}

	
	/**
	 * method for deleting a medical record
	 * 
	 * @param firstName input parameter
	 * @param lastName input parameter
	 */
	public void deleteMedicalRecord(String firstName, String lastName) {

		logger.debug("Deleting for firstName {} and lastName {} ", firstName, lastName);

		medicalRecordDao.deleteMedicalRecord(firstName, lastName);

		logger.info("successfully Deleting medical record {} {} ", firstName, lastName);
	}

}
