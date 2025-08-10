package com.experiment.daeseda_renewal.domain.user;

import com.experiment.daeseda_renewal.constant.ErrorCode;
import com.experiment.daeseda_renewal.domain.user.dto.CreateUserRequest;
import com.experiment.daeseda_renewal.domain.user.dto.DeleteUserRequest;
import com.experiment.daeseda_renewal.domain.user.dto.FindUserEmailRequest;
import com.experiment.daeseda_renewal.domain.user.dto.LoginRequest;
import com.experiment.daeseda_renewal.domain.user.dto.LoginResponse;
import com.experiment.daeseda_renewal.domain.user.dto.ResetUserPasswordRequest;
import com.experiment.daeseda_renewal.domain.user.dto.VerifyUserPasswordResetRequest;
import com.experiment.daeseda_renewal.global.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  @Transactional
  public void signUp(CreateUserRequest request) {

    if (userRepository.existsByUserEmail(request.getUserEmail())) {
      throw new BusinessException(ErrorCode.DUPLICATE_EMAIL);
    }

    User user = User.builder()
                    .userEmail(request.getUserEmail())
                    .userNickname(request.getUserNickname())
                    .userName(request.getUserName())
                    .userPhone(request.getUserPhone())
                    .userPassword(passwordEncoder.encode(request.getUserPassword()))
                    .build();

    userRepository.save(user);
  }

  @Override
  public LoginResponse login(LoginRequest request) {
    User user = userRepository.findByUserEmail(request.getUserEmail())
                              .orElseThrow(
                                  () -> new BusinessException(ErrorCode.LOGIN_VALID_FAILED));

    if (!passwordEncoder.matches(request.getUserPassword(), user.getUserPassword())) {
      throw new BusinessException(ErrorCode.LOGIN_VALID_FAILED);
    }

    return LoginResponse.builder()
                        .userId(user.getUserId())
                        .userEmail(user.getUserEmail())
                        .userNickname(user.getUserNickname())
                        .build();
  }

  @Override
  public String findUserEmail(FindUserEmailRequest request) {
    return userRepository.findUserEmail(request.getUserName(), request.getUserPhone())
                         .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
  }


  @Override
  public boolean isEmailDuplicate(String email) {
    return userRepository.existsByUserEmail(email);
  }

  @Override
  @Transactional
  public void delete(DeleteUserRequest request) {
    User user = userRepository.findByUserEmail(request.getUserEmail())
                              .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

    if (!user.getUserId()
             .equals(request.getUserId())) {
      throw new BusinessException(ErrorCode.USER_ID_MISMATCH);
    }

    if (!passwordEncoder.matches(request.getUserPassword(), user.getUserPassword())) {
      throw new BusinessException(ErrorCode.USER_PASSWORD_NOT_MATCH);
    }

    userRepository.delete(user);
  }

  @Override
  public boolean verifyUserBeforePasswordReset(VerifyUserPasswordResetRequest request) {
    return userRepository.existsByUserNameAndUserEmailAndUserPhone(
        request.getUserName(),
        request.getUserEmail(),
        request.getUserPhone()
    );
  }

  @Override
  @Transactional
  public void resetUserPassword(ResetUserPasswordRequest request) {
    User user = userRepository.findByUserEmail(request.getUserEmail())
                              .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
    user.updatePassword(passwordEncoder.encode(request.getUserPassword()));
  }
}
