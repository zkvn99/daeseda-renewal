package com.experiment.daeseda_renewal.service;

import com.experiment.daeseda_renewal.dto.UserDTO;

public interface UserService {

    void signUp(UserDTO userDTO);
    void signOut();
    UserDTO login(UserDTO userDTO);
    String findEmailByName(String name);
    String findPasswordByEmail(String email);
}
