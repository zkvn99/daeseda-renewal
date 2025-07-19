package com.experiment.daeseda_renewal.domain.user;

import com.experiment.daeseda_renewal.constant.SignupStatus;

public interface UserService {

    SignupStatus signUp(UserDto userDTO);
    void signOut();
    UserDto login(UserDto userDTO);
    String findEmailByName(String name);
    boolean isEmailDuplicate(String email);

    boolean delete(UserDto userDto);

}
