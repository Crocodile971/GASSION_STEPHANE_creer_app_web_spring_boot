package com.openclassrooms.safetyNetAlerts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.safetyNetAlerts.dao.FireStationDAO;
import com.openclassrooms.safetyNetAlerts.model.FireStation;

import lombok.Data;

@Data
@Service
public class FireStationService {

	@Autowired
	private FireStationDAO fireStationDao;
	
	public List<FireStation> getFireStations(){
		return fireStationDao.findAll();
	}

	public FireStation createFireStation(FireStation fireStation) {

		fireStationDao.saveFireStation(fireStation);
		
		return fireStation;
	}
}
