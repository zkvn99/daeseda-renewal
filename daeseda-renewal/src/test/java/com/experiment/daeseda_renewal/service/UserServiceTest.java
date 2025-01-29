package com.experiment.daeseda_renewal.service;

import com.experiment.daeseda_renewal.dto.UserDto;
import com.experiment.daeseda_renewal.entity.User;
import com.experiment.daeseda_renewal.repository.UserRepository;
import com.experiment.daeseda_renewal.service.user.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest
public class UserServiceTest {

    @MockitoBean
    private UserRepository userRepository;

    private UserService userService;

    @Test
    void testSignup() {
        // given
        UserDto userDTO = UserDto.builder()
                .email("email@email.com")
                .name("name")
                .password("password")
                .build();

        User user = User.builder()
                .email(userDTO.getEmail())
                .name(userDTO.getName())
                .password(userDTO.getPassword())
                .build();

        User savedUser = User.builder()
                .email("email@email.com")
                .name("name")
                .password("password")
                .build();

        // when

        // then
    }

}
