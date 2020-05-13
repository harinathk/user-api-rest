package com.cognizant.userapi.exception;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * @author Harinath Kuntamukkala
 */
public class ErrorResponse {
    private final Date timestamp;
    private final String message;
    private final String details;

    public ErrorResponse(Date timestamp, String message, String details) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }

}
