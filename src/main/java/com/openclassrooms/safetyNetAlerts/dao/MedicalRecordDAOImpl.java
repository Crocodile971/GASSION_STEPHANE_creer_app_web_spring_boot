package com.openclassrooms.safetyNetAlerts.dao;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.openclassrooms.safetyNetAlerts.config.JsonDataLoader;
import com.openclassrooms.safetyNetAlerts.model.MedicalRecord;
import com.openclassrooms.safetyNetAlerts.model.Person;

@Repository
public class MedicalRecordDAOImpl implements MedicalRecordDAO {

	private static final Logger logger = LogManager.getLogger(PersonDAOImpl.class);

	JsonDataLoader loader = new JsonDataLoader();
	List<MedicalRecord> medicalRecords = loader.loadMedicalRecordData("data.json");

	@Override
	public List<MedicalRecord> findAll() {

		logger.info("Initialize all medical records data file");
		return medicalRecords;
	}

	@Override
	public MedicalRecord saveMedicalRecord(MedicalRecord medicalRecord) {

		try {
			if (!medicalRecords.contains(medicalRecord)) {
				logger.debug("To debug add medical record {} ", medicalRecord.toString());
				medicalRecords.add(medicalRecord);
				logger.info("successfully save medical record {} ", medicalRecord);
			} else {
				medicalRecords.removeIf(
						removeMedicalRecord -> removeMedicalRecord.getFirstName().equals(medicalRecord.getFirstName())
								&& removeMedicalRecord.getLastName().equals(medicalRecord.getLastName()));
				medicalRecords.add(medicalRecord);

				logger.info("successfully save after remove medical record. {}", medicalRecord);

			}
		} catch (NullPointerException e) {
			logger.error("receive null pointer exception to save medical record ", e);
		} catch (Exception e) {
			logger.error("receive exception to save medical record ", e);
			e.printStackTrace();
		}
		return medicalRecord;
	}

	@Override
	public MedicalRecord findByFirstNameAndLastName(String firstName, String lastName) {

		try {
			logger.trace("About filtering a medical record by first {} and last name {} ", firstName, lastName);

			MedicalRecord medicalRecord = medicalRecords.stream()
					.filter(medicalRecordFilterBy -> medicalRecordFilterBy.getFirstName().equalsIgnoreCase(firstName)
							&& medicalRecordFilterBy.getLastName().equalsIgnoreCase(lastName))
					.findFirst().orElse(null);

			logger.info("We find {} {} successfully {} ", firstName, lastName, medicalRecord);

			return medicalRecord;

		} catch (NullPointerException e) {
			logger.error("failed find By First Name And LastName ", e);
		}
		return null;
	}

	@Override
	public MedicalRecord updateMedicalRecord(MedicalRecord medicalRecordFound, MedicalRecord medicalRecordDetails) {

		logger.debug("Debug to updating medical record");

		if (medicalRecordFound.getBirthdate() != null) {
			medicalRecordFound.setBirthdate(medicalRecordDetails.getBirthdate());
		}

		if (medicalRecordFound.getMedications() != null) {
			medicalRecordFound.setMedications(medicalRecordDetails.getMedications());
		}

		if (medicalRecordFound.getAllergies() != null) {
			medicalRecordFound.setAllergies(medicalRecordDetails.getAllergies());
		}

		logger.info("Updating medical record successfully : {} {} . ", medicalRecordFound.getFirstName(),
				medicalRecordFound.getLastName());
		return medicalRecordFound;
	}

	@Override
	public void deleteMedicalRecord(String firstName, String lastName) {

		logger.debug("Debug remove: firstName: {} and lastName: {} ", firstName, lastName);
		medicalRecords.removeIf(removeMedicalRecord -> removeMedicalRecord.getFirstName().equals(firstName)
				&& removeMedicalRecord.getLastName().equals(lastName));

		logger.info("Deleting {} {} successfully ", firstName, lastName);
	}

}
