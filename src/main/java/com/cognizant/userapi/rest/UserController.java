package com.cognizant.userapi.rest;


import com.cognizant.userapi.exception.UserNotFoundException;
import com.cognizant.userapi.model.User;
import com.cognizant.userapi.model.UserResponse;
import com.cognizant.userapi.repository.UserRepository;
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
 * @author Harinath Kuntamukkala
 */
@RestController
@RequestMapping("/users")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserRepository userRepository;

    UserController(UserRepository repository) {
        this.userRepository = repository;
    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> getUsers(){
        List<User> users =  this.userRepository.findUsersWhoseSalariesBetween0To4000();
        UserResponse userResponse = new UserResponse();
        userResponse.results(users);

        if(userResponse.getResults().isEmpty()){
            throw new UserNotFoundException("Users Not Found");
        }

        return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") long id){
        Optional<User> user = this.userRepository.findById(id);

        return user.map(e -> ResponseEntity.ok(e)) //
                .orElseThrow(() -> new UserNotFoundException("User Id-" + id + " Not Found"));

    }

}
