package com.experiment.daeseda_renewal.domain.user;

import com.experiment.daeseda_renewal.domain.user.dto.CreateUserRequest;
import com.experiment.daeseda_renewal.domain.user.dto.DeleteUserRequest;
import com.experiment.daeseda_renewal.domain.user.dto.FindUserEmailRequest;
import com.experiment.daeseda_renewal.domain.user.dto.LoginRequest;
import com.experiment.daeseda_renewal.domain.user.dto.LoginResponse;
import com.experiment.daeseda_renewal.domain.user.dto.ResetUserPasswordRequest;
import com.experiment.daeseda_renewal.domain.user.dto.VerifyUserPasswordResetRequest;

public interface UserService {

  void signUp(CreateUserRequest request);

  LoginResponse login(LoginRequest request);

  String findUserEmail(FindUserEmailRequest request);

  boolean isEmailDuplicate(String email);

  void delete(DeleteUserRequest request);

  boolean verifyUserBeforePasswordReset(VerifyUserPasswordResetRequest request);

  void resetUserPassword(ResetUserPasswordRequest request);
}
