package com.experiment.daeseda_renewal.service;

import com.experiment.daeseda_renewal.dto.UserDTO;
import com.experiment.daeseda_renewal.entity.User;
import com.experiment.daeseda_renewal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public void signUp(UserDTO userDTO) {
        User user = User.builder()
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .build();
         userRepository.save(user);
    }

    @Override
    public void signOut() {
    }

    @Override
    public UserDTO login(UserDTO userDTO) {
        User user = userRepository.findByEmail(userDTO.getEmail());
        if(user == null) {
            return null;
        } else {
            if(user.getPassword().equals(userDTO.getPassword())) {
                return UserDTO.fromUser(user);
            } else {
                return null;
            }
        }
    }

    @Override
    public String findEmailByName(String name) {
        User user = userRepository.findByName(name);
        if(user == null) {
            return null;
        } else {
            return user.getEmail();
        }
    }

    @Override
    public String findPasswordByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if(user == null) {
            return null;
        } else {
            return user.getPassword();
        }
    }
}
