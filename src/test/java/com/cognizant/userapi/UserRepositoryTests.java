package com.cognizant.userapi;

import com.cognizant.userapi.model.User;
import com.cognizant.userapi.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for {@link UserRepository}
 *
 * @author Harinath Kuntamukkala
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UserRepositoryTests {
    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private UserRepository userRepository;


    @BeforeEach
    public void setUp(){
        // given
        List<User> users =
                Arrays.asList( //
                        new User("Frodo", BigDecimal.valueOf(3500)), //
                        new User("Bilbo", BigDecimal.valueOf(5000)), //
                        new User("James", BigDecimal.valueOf(2600)), //
                        new User("Joseph", BigDecimal.valueOf(4000))
                );

        for (User user : users) {
            testEntityManager.persist(user);
        }
    }

    @Test
    public void whenFindUsersWhoseSalariesWithinTheLimitThenReturnUserList() {
        //when
        List<User> users = userRepository.findBySalary(BigDecimal.valueOf(0), BigDecimal.valueOf(4000));

        //then
        assertThat(users).hasSize(3);
    }

    @Test
    public void whenFindAllThenReturnUserList() {
        //when
        List<User> users = userRepository.findAll();

        //then
        assertThat(users).hasSize(4);
    }

    @Test
    public void whenFindByIdThenReturnUser() {
        // when
        User user = userRepository.findById(1l).get();

        // then
        assertThat(user.getName()).isEqualTo("Frodo");
    }

}
