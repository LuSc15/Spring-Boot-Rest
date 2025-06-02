package com.example.springapidemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;



@SpringBootApplication
public class SpringApiDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringApiDemoApplication.class, args);
	}
	//Erstellt eine Instanz von RestTemplate, die für HTTP-Anfragen verwendet werden kann (in diesem Fall von ConsumingRest)
	//AppConfig.java stellt sicher, dass die Instanz von ConsumingRest erstellt wird, wenn die Anwendung gestartet wird.
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }


}
