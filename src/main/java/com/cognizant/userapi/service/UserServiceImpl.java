package com.cognizant.userapi.service;

import com.cognizant.userapi.model.User;
import com.cognizant.userapi.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * Used as a facade for User controller
 * Also a placeholder for @Transactional and @Cacheable annotations
 *
 * @author Harinath Kuntamukkala
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    /**
     * Constructor Injection
     * @param repository
     */
    UserServiceImpl(final UserRepository repository) {
        this.userRepository = repository;
    }

    @Override
    public Optional<User> findById(final Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<User> findBySalary(final BigDecimal fromSalary, final BigDecimal toSalary) {
        return userRepository.findBySalary(fromSalary, toSalary);
    }

}
