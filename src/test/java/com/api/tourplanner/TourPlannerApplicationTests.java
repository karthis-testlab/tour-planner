package com.api.tourplanner;

import com.api.tourplanner.model.JourneyInformation;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TourPlannerApplicationTests {

	@Autowired
	TestRestTemplate testRestTemplate;

	@Test
	void shouldReturnAllJourneyInformations() {
		ResponseEntity<String> response =  testRestTemplate.getForEntity("/journey/details", String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	void shoouldReturnSingleJourneyInformationBasedOnValidId() {
		ResponseEntity<String> response = testRestTemplate.getForEntity("/journey/detail/1", String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		DocumentContext responseContent = JsonPath.parse(response.getBody());
		Number id = responseContent.read("$.id");
		assertThat(id).isNotNull();
		assertThat(id).isEqualTo(1);
	}

	@Test
	void shouldNotRetrunJourneyInformationWithInValidId() {
		ResponseEntity<String> response = testRestTemplate.getForEntity("/journey/detail/99", String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
	}

	@Test
	void shouldEnterNewJourneyInformation() {
		JourneyInformation newEntry = new JourneyInformation(null, "Madurai", LocalDate.of(2023, 12, 12), LocalDate.of(2023, 12,15));
		ResponseEntity<Void> response = testRestTemplate.postForEntity("/journey/detail/enter", newEntry, Void.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
	}

	@Test
	void shouldUpdateExistingJourneyInformation() {
		JourneyInformation updateEntry = new JourneyInformation(1L, "Vienna", LocalDate.of(2023, 12, 12), LocalDate.of(2023, 12,15));
		HttpEntity<JourneyInformation> requestPayload = new HttpEntity<>(updateEntry);
		ResponseEntity<Void> response = testRestTemplate.exchange("/journey/detail/update/1", HttpMethod.PUT, requestPayload, Void.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	void shouldUpdateExistingJourneyInformationWithInvalidId() {
		JourneyInformation updateEntry = new JourneyInformation(99L, "Vienna", LocalDate.of(2023, 12, 12), LocalDate.of(2023, 12,15));
		HttpEntity<JourneyInformation> requestPayload = new HttpEntity<>(updateEntry);
		ResponseEntity<Void> response = testRestTemplate.exchange("/journey/detail/update/99", HttpMethod.PUT, requestPayload, Void.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
	}

	@Test
	void shouldDeleteExistingJourneyInformation() {
		ResponseEntity<Void> response = testRestTemplate.exchange("/journey/detail/delete/4", HttpMethod.DELETE, null, Void.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
	}

}
