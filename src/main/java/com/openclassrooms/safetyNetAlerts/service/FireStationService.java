package com.openclassrooms.safetyNetAlerts.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.safetyNetAlerts.dao.FireStationDAO;
import com.openclassrooms.safetyNetAlerts.model.FireStation;

@Service
public class FireStationService {

	private static final Logger logger = LogManager.getLogger(FireStationService.class);

	@Autowired
	private FireStationDAO fireStationDao;

	/**
	 * Method to get all fire Stations
	 * 
	 * @return all Station of the list.
	 */
	public List<FireStation> getFireStations() {
		logger.info("List of all fire Stations");
		return fireStationDao.findAll();
	}

	/**
	 * Method for creating a fire Station.
	 * 
	 * @param fireStation Object input parameter
	 * @return The creation of a fire station.
	 */
	public FireStation createFireStation(FireStation fireStation) {

		logger.debug("To debug the creating of fire Station {} ", fireStation.toString());

		fireStationDao.saveFireStation(fireStation);

		logger.info("Successfully save fire Station {} ", fireStation.toString());
		return fireStation;
	}

	/**
	 * method for updating a station with a unique address and station name
	 * identifier
	 * 
	 * @param address        input parameter
	 * @param station        input parameter
	 * @param stationDetails input parameter
	 * @return A station's modification
	 */
	public FireStation updateFireStation(String address, String station, FireStation stationDetails) {

		logger.debug("Update fireStation debug {} {}", address, station);
		FireStation updateFireStation = fireStationDao.findByAddressAndStation(address, station);

		if (updateFireStation != null) {

			fireStationDao.updateFireStation(updateFireStation, stationDetails);

			logger.info("successfully updated station {} {}  ", address, station, stationDetails.toString());

			return fireStationDao.saveFireStation(updateFireStation);
		} else {

			logger.error("Not station to update: {} {} .", address, station);
			return null;

		}
	}

	
	/**
	 * method for deleting a station
	 * 
	 * @param address input parameter
	 * @param station input parameter
	 */
	public void deleteFireStation(String address, String station) {

		logger.debug("Deleting for address {} and station {} ", address, station);

		fireStationDao.deleteFireStation(address, station);

		logger.info("successfully Deleting person {} {} {} ", address, station);
	}
}
