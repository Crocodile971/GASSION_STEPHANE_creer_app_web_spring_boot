package com.openclassrooms.safetyNetAlerts.model;

import lombok.Data;

@Data
public class Person {
	

	public Person() {
		super();
	}

	public Person(String firstName, String lastName, String address, String city, String zip, String phone,
			String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.zip = zip;
		this.phone = phone;
		this.email = email;
	}

	private String firstName;

	private String lastName;

	private String address;

	private String city;

	private String zip;

	private String phone;

	private String email;

	@Override
	public String toString() {
		return "Person [firstName=" + firstName + ", lastName=" + lastName + ", address=" + address + ", city=" + city
				+ ", zip=" + zip + ", phone=" + phone + ", email=" + email + "]";
	}

}
