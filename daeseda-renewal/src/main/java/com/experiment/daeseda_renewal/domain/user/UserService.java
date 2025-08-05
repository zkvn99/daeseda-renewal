package com.experiment.daeseda_renewal.domain.user;

import com.experiment.daeseda_renewal.domain.user.dto.CreateUserRequest;
import com.experiment.daeseda_renewal.domain.user.dto.LoginRequest;
import com.experiment.daeseda_renewal.domain.user.dto.LoginResponse;

public interface UserService {

  void signUp(CreateUserRequest request);

  void signOut();

  LoginResponse login(LoginRequest request);

  String findEmailByName(String name);

  boolean isEmailDuplicate(String email);

  void delete(UserDto userDto);
}
