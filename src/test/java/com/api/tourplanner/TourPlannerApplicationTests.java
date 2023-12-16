package com.api.tourplanner;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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

}
