package com.cognizant.userapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Harinath Kuntamukkala
 */
public class UserResponse {

    @JsonProperty("results")
    @Valid
    private List<User> results = null;

    public UserResponse results(List<User> results) {
        this.results = results;
        return this;
    }

    public List<User> getResults() {
        return results;
    }

    public void setResults(List<User> results) {
        this.results = results;
    }
}
