package com.cognizant.userapi.rest;


import com.cognizant.userapi.exception.UserNotFoundException;
import com.cognizant.userapi.model.User;
import com.cognizant.userapi.model.UserResponse;
import com.cognizant.userapi.repository.UserRepository;
import com.cognizant.userapi.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;


/**
 * API for listing users
 * @author Harinath Kuntamukkala
 */
@RestController
@RequestMapping("/users")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Get all users whose salaries between 0 and 4000
     * @return
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> getUsersWhoseSalariesBetween0To4000(){
        List<User> users =  this.userService.findUsersWhoseSalariesBetween0To4000();
        UserResponse userResponse = new UserResponse();
        userResponse.results(users);

        if(userResponse.getResults().isEmpty()){
            throw new UserNotFoundException("Users Not Found");
        }

        return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);
    }

    /**
     * Get user by ID
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") long id){
        Optional<User> user = this.userService.findById(id);

        return user.map(e -> ResponseEntity.ok(e)) //
                .orElseThrow(() -> new UserNotFoundException("User Id-" + id + " Not Found"));

    }

}
