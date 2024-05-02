package com.openclassrooms.safetyNetAlerts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.safetyNetAlerts.model.FireStation;
import com.openclassrooms.safetyNetAlerts.service.FireStationService;

@RestController
public class FireStationController {
	
	@Autowired
	private FireStationService fireStationService;

	@GetMapping("/fireStation")
	public List<FireStation> getFiresStations(){
		return fireStationService.getFireStations();
	}
	
	@PostMapping("/fireStation")
	public ResponseEntity<FireStation> createFireStation(@RequestBody FireStation fireStation){
		
		FireStation saveFireStation = fireStationService.createFireStation(fireStation);
		
		return new ResponseEntity<>(saveFireStation, HttpStatus.CREATED);
		
	}
}
