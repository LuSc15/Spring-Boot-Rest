package com.example.springapidemo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.springapidemo.RestKontakte.Customer;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class SelectFromPostgres implements CommandLineRunner {
	private static final Logger log = LoggerFactory.getLogger(SelectFromPostgres.class);

	public static void main(String[] args) {

		  
		    SpringApplication.run(SelectFromPostgres.class, args);
	}

		  @Autowired
		  JdbcTemplate jdbcTemplate;
		  
		  @Override
		  public void run(String... strings) throws Exception {
			  log.info("Querying for kontakte records where firstname = 'Luca':");
			    jdbcTemplate.query(
			        "SELECT id, firstname, lastname FROM public.contacts WHERE firstname = ?",
			        (rs, rowNum) -> new Customer(rs.getLong("id"), rs.getString("firstname"), rs.getString("lastname"),rs.getString("mail"),
			        		rs.getString("phone"), rs.getString("zipcode"),rs.getString("city"),rs.getString("gender")), "Luda")
			    .forEach(customer -> log.info(customer.toString()));
		  }
	}


