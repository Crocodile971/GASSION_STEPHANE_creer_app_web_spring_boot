package com.openclassrooms.safetyNetAlerts.model;

import lombok.Data;

@Data
public class FireStation {

	public FireStation() {
		super();
	}

	public FireStation(String address, String station) {
		super();
		this.address = address;
		this.station = station;
	}

	private String address;
	
	private String station;
}
