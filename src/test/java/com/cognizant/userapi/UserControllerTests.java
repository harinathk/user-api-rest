package com.cognizant.userapi;

import com.cognizant.userapi.model.User;
import com.cognizant.userapi.controller.UserController;
import com.cognizant.userapi.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.mockito.Mockito.doReturn;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test class for {@link UserController}
 *
 * @author Harinath Kuntamukkala
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest
public class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    UserService userService;

    @Test
    public void whenFindAllUsersWithinTheSalaryLimitsThenReturnUserList() throws Exception {
        // given
        List<User> users =
            Arrays.asList( //
                    new User(1L, "Frodo", BigDecimal.valueOf(3500)), //
                    new User(2L, "Bilbo", BigDecimal.valueOf(5000)), //
                    new User(3L, "James", BigDecimal.valueOf(2600)), //
                    new User(4L, "Joseph", BigDecimal.valueOf(4000))
            );

        doReturn(users).when(userService).findBySalary(BigDecimal.valueOf(0), BigDecimal.valueOf(4000));

        // when + then
        this.mockMvc.perform(get("/users/salary?from=0&to=4000"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.results.size()", is(4)))
                .andExpect(jsonPath("$.results[2].name", is("James")))
                .andExpect(jsonPath("$.results[2].salary", is(2600)));
    }

    @Test
    public void whenNotFindAllUsersWithinTheSalaryLimitsThenReturnUserList() throws Exception {
        // given
        List<User> users =
                Arrays.asList( //
                        new User(1L, "Frodo", BigDecimal.valueOf(3500)), //
                        new User(2L, "Bilbo", BigDecimal.valueOf(5000)), //
                        new User(3L, "James", BigDecimal.valueOf(2600)), //
                        new User(4L, "Joseph", BigDecimal.valueOf(4000))
                );

        doReturn(users).when(userService).findBySalary(BigDecimal.valueOf(0), BigDecimal.valueOf(4000));

        // when + then
        this.mockMvc.perform(get("/users/salary?from=0&to=4000"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.results.size()", is(not(5))))
                .andExpect(jsonPath("$.results[2].name", is(not("Hari"))));
    }


    @Test
    public void whenFindByIdThenReturnUser() throws Exception {
        // given
        User user = new User(1l, "Frodo", BigDecimal.valueOf(3500));

        doReturn(Optional.of(user)).when(userService).findById(1l);

        // when + then
        this.mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(user.getName())));
    }

    @Test
    public void whenNotFindByIdThenDontReturnUser() throws Exception {
        // given
        User user = new User(1l, "Frodo", BigDecimal.valueOf(3500));

        doReturn(Optional.of(user)).when(userService).findById(1l);

        // when + then
        this.mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(not("Hari"))));
    }

}