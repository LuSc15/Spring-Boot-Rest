package com.example.springapidemo.northwindAPI.models.ExceptionHandling;

import jakarta.servlet.http.HttpServletRequest;

public class InvalidYearException extends RuntimeException {
	private final String clientIp;

    public InvalidYearException(String message, HttpServletRequest request) {
        super(message);
        this.clientIp = request.getRemoteAddr();
    }
    public String getClientIp() {
		return clientIp;
	}
}
