package com.openclassrooms.safetyNetAlerts.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.openclassrooms.safetyNetAlerts.model.FireStation;

@Repository
public interface FireStationDAO {

	public List<FireStation> findAll();

	public FireStation saveFireStation(FireStation fireStation);
	
	public FireStation findByAddressAndStation(String address, String station);

	public FireStation updateFireStation(FireStation fireStationFound, FireStation stationDetails);
	
	public void deleteFireStation(String address, String station); 
} 
 