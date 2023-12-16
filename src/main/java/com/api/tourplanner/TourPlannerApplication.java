package com.api.tourplanner;

import com.api.tourplanner.model.JourneyInformation;
import com.api.tourplanner.repository.JourneyCore;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

import java.time.LocalDate;

@SpringBootApplication
@EnableJdbcRepositories
public class TourPlannerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TourPlannerApplication.class, args);
	}

	@Bean
	public ApplicationRunner applicationRunner(JourneyCore journeyCore) {
		return args -> {

			journeyCore.save(
					new JourneyInformation(null, "Vienna",
							LocalDate.of(2024, 04, 12),
							LocalDate.of(2024, 04, 12))
			);

			journeyCore.save(
					new JourneyInformation(null, "New Delhi",
							LocalDate.of(2024, 04, 13),
							LocalDate.of(2024, 04, 13))
			);

			journeyCore.save(
					new JourneyInformation(null, "Rajapalayam",
							LocalDate.of(2024, 04, 14),
							LocalDate.of(2024, 04, 19))
			);

			journeyCore.save(
					new JourneyInformation(null, "Chennai",
							              LocalDate.of(2024, 04, 20),
							              LocalDate.of(2024, 04, 29))
			);

		};
	}

}
