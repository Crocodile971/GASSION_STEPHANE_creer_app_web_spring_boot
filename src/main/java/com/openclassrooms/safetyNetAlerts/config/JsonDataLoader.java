package com.openclassrooms.safetyNetAlerts.config;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetyNetAlerts.model.FireStation;
import com.openclassrooms.safetyNetAlerts.model.Person;

public class JsonDataLoader {

	private static final Logger logger = LogManager.getLogger(JsonDataLoader.class);

	public List<Person> loadPersonData(String resourcePath) {

		ObjectMapper objectMapper = new ObjectMapper();
		Resource ressource = new ClassPathResource(resourcePath);

		TypeReference<List<Person>> typeReference = new TypeReference<List<Person>>() {
		};
		try {
			JsonNode rootNode = objectMapper.readTree(ressource.getInputStream()).path("persons");

			return objectMapper.convertValue(rootNode, typeReference);

		} catch (IOException e) {
			logger.error("Cannot display data.json file ", e);
			e.printStackTrace();

		}
		return null;
	}

	public List<FireStation> loadFireStationData(String resourcePath) {

		ObjectMapper objectMapper = new ObjectMapper();
		Resource ressource = new ClassPathResource(resourcePath);

		TypeReference<List<FireStation>> typeReference = new TypeReference<List<FireStation>>() {
		};
		try {
			JsonNode rootNode = objectMapper.readTree(ressource.getInputStream()).path("firestations");

			return objectMapper.convertValue(rootNode, typeReference);

		} catch (IOException e) {
			logger.error("Cannot display data.json file ", e);
			e.printStackTrace();

		}
		return null;
	}

}
