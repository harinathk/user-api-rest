package com.cognizant.userapi.service;

import com.cognizant.userapi.model.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * User Service works a facade for User controller
 *
 * @author Harinath Kuntamukkkala
 */
public interface UserService {
    Optional<User> findById(final Long id);
    List<User> findBySalary(final BigDecimal fromSalary, final BigDecimal toSalary);
    List<User> findAll();
}
