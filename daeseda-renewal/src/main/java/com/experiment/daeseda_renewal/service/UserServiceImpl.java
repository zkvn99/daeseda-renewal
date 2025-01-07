package com.experiment.daeseda_renewal.service;

import com.experiment.daeseda_renewal.dto.UserDTO;
import com.experiment.daeseda_renewal.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    @Override
    public int signUp(UserDTO userDTO) {
        return 0;
    }

    @Override
    public int signOut() {
        return 0;
    }
}
