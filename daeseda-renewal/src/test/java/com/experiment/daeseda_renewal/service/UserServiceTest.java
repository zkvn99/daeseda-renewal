package com.experiment.daeseda_renewal.service;

import com.experiment.daeseda_renewal.domain.user.User;
import com.experiment.daeseda_renewal.domain.user.UserRepository;
import com.experiment.daeseda_renewal.domain.user.UserService;
import com.experiment.daeseda_renewal.domain.user.dto.CreateUserRequest;
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
    CreateUserRequest request = CreateUserRequest.builder()
                                                 .userEmail("email@email.com")
                                                 .userName("name")
                                                 .userPassword("password")
                                                 .userNickname("nickname")
                                                 .userPhone("0101412452")
                                                 .build();

    User user = User.builder()
                    .userEmail(request.getUserEmail())
                    .userName(request.getUserName())
                    .userPassword(request.getUserPassword())
                    .userNickname(request.getUserNickname())
                    .userPhone(request.getUserPhone())
                    .build();

    User savedUser = User.builder()
                         .userEmail("email@email.com")
                         .userName("name")
                         .userPassword("password")
                         .userNickname("nickname")
                         .userPhone("0101412452")
                         .build();

    // when

    // then
  }

}
