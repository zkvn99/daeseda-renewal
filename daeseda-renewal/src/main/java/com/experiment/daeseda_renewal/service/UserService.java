package com.experiment.daeseda_renewal.service;

import com.experiment.daeseda_renewal.dto.UserDTO;

public interface UserService {

    int signUp(UserDTO userDTO);
    int signOut();
}
