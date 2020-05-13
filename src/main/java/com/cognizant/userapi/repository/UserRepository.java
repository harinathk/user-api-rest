package com.cognizant.userapi.repository;

import com.cognizant.userapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Harinath Kuntamukkala
 */
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.salary BETWEEN 0 and 4000")
    List<User> findUsersWhoseSalariesBetween0To4000();
}
