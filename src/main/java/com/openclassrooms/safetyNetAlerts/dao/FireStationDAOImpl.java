package com.openclassrooms.safetyNetAlerts.dao;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.openclassrooms.safetyNetAlerts.config.JsonDataLoader;
import com.openclassrooms.safetyNetAlerts.model.FireStation;

@Repository
public class FireStationDAOImpl implements FireStationDAO {

	private static final Logger logger = LogManager.getLogger("PersonDAOImpl");

	JsonDataLoader loader = new JsonDataLoader();
	List<FireStation> fireStations = loader.loadFireStationData("data.json");

	@Override
	public List<FireStation> findAll() {
		logger.info("Initialize all fire stations data file");
		return fireStations;
	}

	@Override
	public FireStation saveFireStation(FireStation fireStation) {

		try {
			if (!fireStations.contains(fireStation)) {
				fireStations.add(fireStation);
			} else {
				throw new Exception("Failed to save fireStation");
			}
		} catch (NullPointerException e) {
			logger.error("receive null pointer exception ", e);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fireStation;
	}

	@Override
	public void deleteFireStation(String address, String station) {
		// TODO Auto-generated method stub

	}

}
