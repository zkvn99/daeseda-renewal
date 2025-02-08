package com.experiment.daeseda_renewal.service.user;

import com.experiment.daeseda_renewal.constant.SignupStatus;
import com.experiment.daeseda_renewal.dto.UserDto;

public interface UserService {

    SignupStatus signUp(UserDto userDTO);
    void signOut();
    UserDto login(UserDto userDTO);
    String findEmailByName(String name);
    boolean isEmailDuplicate(String email);
}
