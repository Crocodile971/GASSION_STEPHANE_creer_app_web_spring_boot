package com.openclassrooms.safetyNetAlerts.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.openclassrooms.safetyNetAlerts.dao.FireStationDAO;
import com.openclassrooms.safetyNetAlerts.model.FireStation;

@ExtendWith(MockitoExtension.class)
public class FireStationServiceTest {

	@Mock
	private FireStationDAO fireStationDao;

	@InjectMocks
	private FireStationService fireStaionService;

	private FireStation fireStation;

	@BeforeEach
	public void setup() {
		fireStation = new FireStation("135 rue des trolls", "5");
	}

	@DisplayName("Test for get all stations")
	@Test
	public void testGetAllFireStations() {

		// given
		FireStation fireStation1 = new FireStation("13 voie des lumiere", "7");

		when(fireStationDao.findAll()).thenReturn(List.of(fireStation, fireStation1));

		// when
		List<FireStation> stationList = fireStaionService.getFireStations();

		// then
		assertThat(stationList).isNotNull();

	}

	@DisplayName("Test for create station method")
	@Test
	public void testToCreateStation() {
		// given
		when(fireStationDao.saveFireStation(fireStation)).thenReturn(fireStation);

		// when
		FireStation saveStation = fireStaionService.createFireStation(fireStation);

		// then
		assertThat(saveStation).isNotNull();
	}

	@DisplayName("Test for update station method")
	@Test
	public void testToUpdateStation() {
		// given
		when(fireStationDao.findByAddressAndStation("135 rue des trolls", "5")).thenReturn(fireStation);
		when(fireStationDao.saveFireStation(fireStation)).thenReturn(fireStation);
		fireStation.setStation("10");

		// when
		FireStation updateStation = fireStaionService.updateFireStation("135 rue des trolls", "5", fireStation);

		// then
		assertThat(updateStation.getStation()).isEqualTo("10");
	}

	@DisplayName("Test for delete station method")
	@Test
	public void testToDeleteFireStation() {
		// given
		String address = fireStation.getAddress();
		String station = fireStation.getStation();

		fireStationDao.deleteFireStation(address, station);

		// when
		fireStaionService.deleteFireStation(address, station);

		// then
		verify(fireStationDao, times(2)).deleteFireStation(address, station);
	}

}
