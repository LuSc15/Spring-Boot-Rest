package com.example.springapidemo.RestKontakte;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;

import com.example.springapidemo.RestKontakte.Models.Contact;

public class ConsumingRest implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(ConsumingRest.class);

	@Autowired
	RestTemplate restTemplate;

	@Override
	public void run(String... args) throws Exception {
			 Contact[] contacts = restTemplate.getForObject("http://localhost:8080/getContacts", Contact[].class);
		        for (Contact contact : contacts) {
		            log.info(contact.toString());
		        }

	}

}
