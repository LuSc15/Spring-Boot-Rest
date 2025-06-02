package com.example.springapidemo.northwindAPI.models.ExceptionHandling;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(InvalidYearException.class)
	public ResponseEntity<Map<String, String>> handleInvalidYearException(InvalidYearException e) {
		Map<String, String> errorResponse = new HashMap<>();

		

		logger.error("Exception ausgelöst: \n Aufrufende IP:"+e.getClientIp(), e);
		errorResponse.put("error", e.getMessage());
		errorResponse.put("message", "Bitte geben Sie eine gültige Jahreszahl ein.");
		return ResponseEntity.badRequest().body(errorResponse); // HTTP 400 Bad Request
	}
}
