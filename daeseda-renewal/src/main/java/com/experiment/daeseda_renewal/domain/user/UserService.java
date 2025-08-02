package com.experiment.daeseda_renewal.domain.user;

public interface UserService {

  void signUp(UserDto userDTO);

  void signOut();

  UserDto login(UserDto userDTO);

  String findEmailByName(String name);

  boolean isEmailDuplicate(String email);

  void delete(UserDto userDto);
}
