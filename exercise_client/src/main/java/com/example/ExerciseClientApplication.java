package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ExerciseClientApplication {

	private static final Logger log = LoggerFactory.getLogger(ExerciseClientApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ExerciseClientApplication.class, args);
		RestTemplate restTemplate = new RestTemplate();
		String echo = restTemplate.postForObject("http://localhost:8080/echo", "foo", String.class);
		log.info(echo);
	}

}
