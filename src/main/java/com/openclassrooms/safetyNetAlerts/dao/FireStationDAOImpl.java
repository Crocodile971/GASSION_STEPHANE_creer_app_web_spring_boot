package com.openclassrooms.safetyNetAlerts.dao;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.openclassrooms.safetyNetAlerts.config.JsonDataLoader;
import com.openclassrooms.safetyNetAlerts.model.FireStation;
import com.openclassrooms.safetyNetAlerts.model.Person;

@Repository
public class FireStationDAOImpl implements FireStationDAO {

	private static final Logger logger = LogManager.getLogger("FireStationDAOImpl");

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
				logger.debug("To debug add fireStation {} ", fireStation.toString());
				fireStations.add(fireStation);
				logger.info("successfully save station {} ", fireStation);
			} else {
				fireStations.removeIf(removeStation -> removeStation.getAddress().equals(fireStation.getAddress())
						&& removeStation.getStation().equals(fireStation.getStation()));
				fireStations.add(fireStation);

				logger.info("successfully save after remove person. {}", fireStation);
			}
		} catch (NullPointerException e) {
			logger.error("receive null pointer exception to save station ", e);
		} catch (Exception e) {
			logger.error("receive exception to save station ", e);
			e.printStackTrace();
		}
		return fireStation;
	}

	@Override
	public FireStation findByAddressAndStation(String address, String station) {
		try {
			logger.trace("About filtering a station by address {} and station name {} ", address, station);

			FireStation fireStation = fireStations.stream()
					.filter(stationFilterBy -> stationFilterBy.getAddress().equalsIgnoreCase(address)
							&& stationFilterBy.getStation().equalsIgnoreCase(station)).findFirst().orElse(null);

			logger.info("We find {} {} successfully {} ", address, station, fireStation);
			
			return fireStation;     
 
		} catch (NullPointerException e) {
			logger.error("failed find By address Name And station ", e);
		}
		return null;
	}

	@Override
	public FireStation updateFireStation(FireStation fireStationFound, FireStation stationDetails) {

		logger.debug("Debug to updating station"); 
		
		if(fireStationFound.getStation() != null) {
			fireStationFound.setStation(stationDetails.getStation());
		}
		
		logger.info("Updating station successfully : {} {} . ", fireStationFound.getAddress(), fireStationFound.getStation());
		return fireStationFound;
	}

	@Override
	public void deleteFireStation(String address, String station) {

		logger.debug("Debug remove: address: {} and station: {} ", address, station);
		fireStations.removeIf(removeStation -> removeStation.getAddress().equals(address)
				&& removeStation.getStation().equals(station));

		logger.info("Deleting {} {} successfully ", address, station);
	}

}
