package com.cognizant.userapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * User not found exception servers for Users and user by id
 *
 * @author Harinath Kuntamukkala
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(final String exception) {
        super(exception);
    }

}
