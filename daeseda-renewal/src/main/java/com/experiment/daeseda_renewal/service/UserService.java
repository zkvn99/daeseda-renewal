package com.experiment.daeseda_renewal.service;

import com.experiment.daeseda_renewal.dto.UserDTO;

public interface UserService {

    void signUp(UserDTO userDTO);
    void signOut();
}
