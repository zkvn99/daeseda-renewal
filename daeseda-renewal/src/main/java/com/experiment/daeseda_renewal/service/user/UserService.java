package com.experiment.daeseda_renewal.service.user;

import com.experiment.daeseda_renewal.dto.UserDto;

public interface UserService {

    void signUp(UserDto userDTO);
    void signOut();
    UserDto login(UserDto userDTO);
    String findEmailByName(String name);
    String findPasswordByEmail(String email);
}
