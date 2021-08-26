package com.github.hgxey6.weather;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WeatherAppSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherAppSpringBootApplication.class, args);
	}

}
