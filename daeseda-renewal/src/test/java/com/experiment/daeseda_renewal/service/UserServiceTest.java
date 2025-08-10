package com.experiment.daeseda_renewal.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.experiment.daeseda_renewal.domain.user.User;
import com.experiment.daeseda_renewal.domain.user.UserRepository;
import com.experiment.daeseda_renewal.domain.user.UserServiceImpl;
import com.experiment.daeseda_renewal.domain.user.dto.CreateUserRequest;
import com.experiment.daeseda_renewal.domain.user.dto.DeleteUserRequest;
import com.experiment.daeseda_renewal.domain.user.dto.FindUserEmailRequest;
import com.experiment.daeseda_renewal.domain.user.dto.LoginRequest;
import com.experiment.daeseda_renewal.domain.user.dto.LoginResponse;
import com.experiment.daeseda_renewal.domain.user.dto.ResetUserPasswordRequest;
import com.experiment.daeseda_renewal.domain.user.dto.VerifyUserPasswordResetRequest;
import com.experiment.daeseda_renewal.global.exception.BusinessException;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

  @InjectMocks
  private UserServiceImpl userService;

  @Mock
  private UserRepository userRepository;

  @Mock
  private PasswordEncoder passwordEncoder;

  @Test
  void signUp_성공() {

    // given
    CreateUserRequest request = CreateUserRequest.builder()
                                                 .userPhone("123456789")
                                                 .userNickname("userNickname")
                                                 .userEmail("userEmail")
                                                 .userPassword(passwordEncoder.encode("password"))
                                                 .userName("userName")
                                                 .build();

    // when
    when(userRepository.existsByUserEmail(request.getUserEmail())).thenReturn(false);
    when(passwordEncoder.encode(request.getUserPassword())).thenReturn("password");

    userService.signUp(request);

    // then
    verify(userRepository).save(any(User.class));
  }

  @Test
  void signUp_중복이메일_예외() {

    // given
    CreateUserRequest request = CreateUserRequest.builder()
                                                 .userPhone("123456789")
                                                 .userNickname("userNickname")
                                                 .userEmail("userEmail")
                                                 .userPassword(passwordEncoder.encode("password"))
                                                 .userName("userName")
                                                 .build();

    // when
    when(userRepository.existsByUserEmail(request.getUserEmail())).thenReturn(true);

    // then
    assertThrows(BusinessException.class, () -> userService.signUp(request));
  }

  @Test
  void login_성공() {

    // given
    LoginRequest request = LoginRequest.builder()
                                       .userEmail("test@test.com")
                                       .userPassword("encodedPwd")
                                       .build();
    User user = User.builder()
                    .userEmail("test@test.com")
                    .userPassword("encodedPwd")
                    .userNickname("닉네임")
                    .userId(1L)
                    .build();

    // when
    when(userRepository.findByUserEmail(request.getUserEmail())).thenReturn(Optional.of(user));
    when(passwordEncoder.matches(request.getUserPassword(), user.getUserPassword())).thenReturn(
        true);

    LoginResponse response = userService.login(request);

    // then
    assertEquals("test@test.com", response.getUserEmail());
    assertEquals("닉네임", response.getUserNickname());
  }

  @Test
  void login_비밀번호_불일치_예외() {

    // given
    LoginRequest request = LoginRequest.builder()
                                       .userEmail("test@test.com")
                                       .userPassword("encodedPwd")
                                       .build();
    User user = User.builder()
                    .userPassword("encodedPwd")
                    .build();

    // when
    when(userRepository.findByUserEmail(request.getUserEmail())).thenReturn(Optional.of(user));
    when(passwordEncoder.matches(request.getUserPassword(), user.getUserPassword())).thenReturn(
        false);

    // then
    assertThrows(BusinessException.class, () -> userService.login(request));
  }

  @Test
  void findUserEmail_성공() {

    // given
    FindUserEmailRequest request = FindUserEmailRequest.builder()
                                                       .userName("userName")
                                                       .userPhone("123456789")
                                                       .build();

    // when
    when(userRepository.findUserEmail("userName", "123456789")).thenReturn(
        Optional.of("test@test.com"));

    String email = userService.findUserEmail(request);

    // then
    assertEquals("test@test.com", email);
  }

  @Test
  void findUserEmail_실패() {

    // given
    FindUserEmailRequest request = FindUserEmailRequest.builder()
                                                       .userName("userName")
                                                       .userPhone("123456789")
                                                       .build();

    // when
    when(userRepository.findUserEmail(any(), any())).thenReturn(Optional.empty());

    // then
    assertThrows(BusinessException.class, () -> userService.findUserEmail(request));
  }

  @Test
  void isEmailDuplicate_true() {

    // given && when
    when(userRepository.existsByUserEmail("test@test.com")).thenReturn(true);

    // then
    assertTrue(userService.isEmailDuplicate("test@test.com"));
  }

  @Test
  void isEmailDuplicate_false() {

    // given && when
    when(userRepository.existsByUserEmail("test@test.com")).thenReturn(false);

    // then
    assertFalse(userService.isEmailDuplicate("test@test.com"));
  }

  @Test
  void delete_성공() {

    // given
    DeleteUserRequest request = DeleteUserRequest.builder()
                                                 .userId(1L)
                                                 .userEmail("test@test.com")
                                                 .userPassword("password")
                                                 .build();
    User user = User.builder()
                    .userId(1L)
                    .userEmail("test@test.com")
                    .userPassword("encodedPwd")
                    .build();

    // when
    when(userRepository.findByUserEmail(request.getUserEmail())).thenReturn(Optional.of(user));
    when(passwordEncoder.matches("password", "encodedPwd")).thenReturn(true);

    userService.delete(request);

    // then
    verify(userRepository).delete(user);
  }

  @Test
  void verifyUserBeforePasswordReset_성공() {
    VerifyUserPasswordResetRequest request = VerifyUserPasswordResetRequest.builder()
                                                                           .userEmail(
                                                                               "test@test.com")
                                                                           .userName("name")
                                                                           .userPhone("123452626")
                                                                           .build();

    when(userRepository.existsByUserNameAndUserEmailAndUserPhone(any(), any(), any())).thenReturn(
        true);

    assertTrue(userService.verifyUserBeforePasswordReset(request));
  }

  @Test
  void resetUserPassword_성공() {
    ResetUserPasswordRequest request = ResetUserPasswordRequest.builder()
                                                               .userEmail("test@test.com")
                                                               .userPassword("newPassword")
                                                               .build();
    User user = User.builder()
                    .userEmail("test@test.com")
                    .userPassword("oldEncoded")
                    .build();

    when(userRepository.findByUserEmail("test@test.com")).thenReturn(Optional.of(user));
    when(passwordEncoder.encode("newPassword")).thenReturn("newEncoded");

    userService.resetUserPassword(request);

    assertEquals("newEncoded", user.getUserPassword());
  }


}
