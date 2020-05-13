package com.cognizant.userapi.service;

import com.cognizant.userapi.model.User;
import com.cognizant.userapi.repository.UserRepository;
import com.cognizant.userapi.rest.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Used as a facade for User controller
 * Also a placeholder for @Transactional and @Cacheable annotations
 *
 * @author Harinath Kuntamukkala
 */
@Service
public class UserServiceImpl implements UserService{

    Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserRepository userRepository;

    UserServiceImpl(UserRepository repository) {
        this.userRepository = repository;
    }

    @Override
    public List<User> findUsersWhoseSalariesBetween0To4000() {
        return userRepository.findUsersWhoseSalariesBetween0To4000();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

}
