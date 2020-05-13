package com.cognizant.userapi.repository;

import com.cognizant.userapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * User Repository
 * Use JPA provided methods and also add custom methods
 *
 * @author Harinath Kuntamukkala
 */
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.salary BETWEEN :fromSalary and :toSalary")
    List<User> findBySalary(final @Param("fromSalary") BigDecimal fromSalary, final @Param("toSalary") BigDecimal toSalary);

}
