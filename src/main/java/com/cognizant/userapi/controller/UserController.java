package com.cognizant.userapi.controller;

import com.cognizant.userapi.exception.UserNotFoundException;
import com.cognizant.userapi.model.User;
import com.cognizant.userapi.model.UserResponse;
import com.cognizant.userapi.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * API for Users
 *
 * @author Harinath Kuntamukkala
 */
@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    private final UserService userService;

    /**
     * Constructor Injection
     * @param userService
     */
    UserController(final UserService userService) {
        this.userService = userService;
    }

    /**
     * Get All Users
     * @return
     */
    @GetMapping
    public ResponseEntity<UserResponse> getUsers(){
        List<User> users =  this.userService.findAll();
        return getUserResponseResponse(users);
    }

    /**
     * Get Users within the salary range
     * @param fromSalary
     * @param toSalary
     * @return
     */
    @GetMapping("/salary")
    public ResponseEntity<UserResponse> getUsersBySalary(final @RequestParam("from") BigDecimal fromSalary,
                                                 final @RequestParam("to") BigDecimal toSalary){
        List<User> users = new ArrayList<>();

        userService.findBySalary(fromSalary, toSalary).forEach(users::add);

        return getUserResponseResponse(users);
    }

    public ResponseEntity<UserResponse> getUserResponseResponse(final List<User> users) {
        UserResponse userResponse = new UserResponse(users);

        if(userResponse.getResults().isEmpty()){
            log.info("Users Not Found");
            throw new UserNotFoundException("Users Not Found");
        }
        return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);
    }

    /**
     * Get User by Id
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<User> getUser(final @PathVariable("id") long id){
        Optional<User> user = this.userService.findById(id);

        return user.map(e -> ResponseEntity.ok(e)) //
                .orElseThrow(() -> new UserNotFoundException("User Id-" + id + " Not Found"));

    }

}
