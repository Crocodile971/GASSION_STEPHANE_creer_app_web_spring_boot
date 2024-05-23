package com.openclassrooms.safetyNetAlerts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.safetyNetAlerts.model.Person;
import com.openclassrooms.safetyNetAlerts.service.PersonService;

@RestController
public class PersonController {

	@Autowired
	private PersonService personService;

	@GetMapping("/person")
	public List<Person> getPersons() {
		return personService.getPersons();
	}

	@PostMapping("/person")
	public ResponseEntity<Person> createPreson(@RequestBody Person person) {

		Person savePerson = personService.createPerson(person);

		return new ResponseEntity<>(savePerson, HttpStatus.CREATED);
	}

	@PutMapping("/person/{firstName} {lastName}")
	public ResponseEntity<Person> updatePerson(@PathVariable(name = "firstName") String firstName,
			@PathVariable(name = "lastName") String lastName, @RequestBody Person personDetails) {

		if (personDetails != null) {

			Person personUpdate = personService.updatePerson(firstName, lastName, personDetails);

			return ResponseEntity.ok(personUpdate);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/person/{firstName} {lastName}")
	public ResponseEntity<String> deletePerson(@PathVariable(name = "firstName") String firstName,
			@PathVariable(name = "lastName") String lastName) {

		personService.deletePerson(firstName, lastName);

		return ResponseEntity.ok("Person deleted successfully!.");

	}

}
