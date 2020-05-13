package com.cognizant.userapi.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Error response model
 *
 * @author Harinath Kuntamukkala
 */
@Getter
@AllArgsConstructor
public class ErrorResponse {
    private final String code;
    private final String message;
    private final String details;
}
