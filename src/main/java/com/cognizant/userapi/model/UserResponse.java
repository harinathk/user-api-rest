package com.cognizant.userapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import java.util.List;

/**
 * User response model
 *
 * @author Harinath Kuntamukkala
 */
@Getter
@Setter
@AllArgsConstructor
public class UserResponse {

    @JsonProperty("results")
    @Valid
    List<User> results = null;
}
