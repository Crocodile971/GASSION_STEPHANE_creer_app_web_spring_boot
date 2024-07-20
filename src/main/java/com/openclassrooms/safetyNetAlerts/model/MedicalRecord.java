package com.openclassrooms.safetyNetAlerts.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class MedicalRecord {

	private String firstName;

	private String LastName; 

	private String birthdate;

	List<String> medications = new ArrayList<String>();

	List<String> allergies = new ArrayList<String>(); 

	public MedicalRecord() {
		super();
	}
	public MedicalRecord(String firstName, String lastName, String birthdate, List<String> medications,
			List<String> allergies) {
		super();
		this.firstName = firstName;
		LastName = lastName;
		this.birthdate = birthdate;
		this.medications = medications;
		this.allergies = allergies;
	}
	

	@Override
	public String toString() {
		return "MedicalRecord [firstName=" + firstName + ", LastName=" + LastName + ", birthdate=" + birthdate
				+ ", medications=" + medications + ", allergies=" + allergies + "]";
	}




	
	

}
