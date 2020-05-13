package com.cognizant.userapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


/**
 * Global ResponseEntity Exception Handler
 *
 * @author Harinath Kuntamukkala
 */
@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String INTERNAL_SERVER_ERROR  = "500";
    private static final String NOT_FOUND  = "204";

    /**
     * User not found exception servers for All users and user by id
     * @param ex
     * @param request
     * @return ResponseEntity
     */
    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<ErrorResponse> handleUserNotFoundException(final UserNotFoundException ex, final WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(NOT_FOUND, ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * Top level Exception handler
     * @param ex
     * @param request
     * @return ResponseEntity
     */
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorResponse> handleAllExceptions(final Exception ex, final WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(INTERNAL_SERVER_ERROR, ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Runtime Exception handler
     * @param e
     * @return ResponseEntity
     */
    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<String> handleRunTimeException(final RuntimeException e) {
        return error(HttpStatus.INTERNAL_SERVER_ERROR, e);
    }

    /**
     * error handler
     * @param status
     * @param e
     * @return ResponseEntity
     */
    private ResponseEntity<String> error(final HttpStatus status, final Exception e) {
        return ResponseEntity.status(status).body(e.getMessage());
    }

}

