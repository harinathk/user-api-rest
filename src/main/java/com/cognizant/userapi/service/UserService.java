package com.cognizant.userapi.service;

import com.cognizant.userapi.model.User;

import java.util.List;
import java.util.Optional;

/**
 * @author Harinath Kuntamukkkala
 */
public interface UserService {
    List<User> findUsersWhoseSalariesBetween0To4000();
    Optional<User> findById(Long id);
}
