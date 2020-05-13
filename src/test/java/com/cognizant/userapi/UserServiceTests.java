package com.cognizant.userapi;

import com.cognizant.userapi.model.User;
import com.cognizant.userapi.repository.UserRepository;
import com.cognizant.userapi.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void shouldReturnFindAll(){
        // given
        List<User> users =
                Arrays.asList( //
                        new User(1L, "Frodo", BigDecimal.valueOf(3500)), //
                        new User(2L, "Bilbo", BigDecimal.valueOf(5000)), //
                        new User(3L, "James", BigDecimal.valueOf(2600)), //
                        new User(4L, "Joseph", BigDecimal.valueOf(4000))
                );

        given(userRepository.findAll()).willReturn(users);

        List<User> expected = userService.findAll();

        assertEquals(expected, users);
    }

    @Test
    void shouldReturnFindUsersWithinTheSalaryLimits(){
        // given
        List<User> users =
                Arrays.asList( //
                        new User(1L, "Frodo", BigDecimal.valueOf(3500)), //
                        new User(2L, "Bilbo", BigDecimal.valueOf(5000)), //
                        new User(3L, "James", BigDecimal.valueOf(2600)), //
                        new User(4L, "Joseph", BigDecimal.valueOf(4000))
                );


        given(userRepository.findBySalary(BigDecimal.valueOf(0),BigDecimal.valueOf(4000))).willReturn(users);

        List<User> expected = userService.findBySalary(BigDecimal.valueOf(0),BigDecimal.valueOf(4000));

        assertEquals(expected, users);
    }
}
