package com.openclassrooms.safetyNetAlerts.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.openclassrooms.safetyNetAlerts.model.FireStation;

@Repository
public interface FireStationDAO {

	public List<FireStation> findAll();

	FireStation saveFireStation(FireStation fireStation);
	
	public void deleteFireStation(String address, String station);
}
