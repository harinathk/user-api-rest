package com.cognizant.userapi.exception;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * @author Harinath Kuntamukkala
 */
public class ErrorResponse {
    private final String code;
    private final String message;
    private final String details;

    public ErrorResponse(String code, String message, String details) {
        super();
        this.code = code;
        this.message = message;
        this.details = details;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }

}
