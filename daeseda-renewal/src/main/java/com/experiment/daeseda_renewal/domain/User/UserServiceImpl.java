package com.experiment.daeseda_renewal.domain.User;

import com.experiment.daeseda_renewal.constant.SignupStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public SignupStatus signUp(UserDto userDTO) {

        if (userRepository.existsByEmail(userDTO.getEmail())) {
            return  SignupStatus.EMAIL_ALREADY_EXISTS;
        }

        User user = User.builder()
                    .name(userDTO.getName())
                    .email(userDTO.getEmail())
                    .password(passwordEncoder.encode(userDTO.getPassword()))
                    .build();
        try {
            userRepository.save(user);
            return SignupStatus.SUCCESS;
        } catch (Exception e) {
            return SignupStatus.FAIL;
        }
    }

    @Override
    public void signOut() {
    }

    @Override
    public UserDto login(UserDto userDTO) {
        User user = userRepository.findByEmail(userDTO.getEmail());
        if(user == null) {
            return null;
        } else {
            if(passwordEncoder.matches(userDTO.getPassword(), user.getPassword())) {
                return UserDto.fromUser(user);
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
    public boolean isEmailDuplicate(String email) {
        return userRepository.existsByEmail(email);
    }
}
