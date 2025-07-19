package com.experiment.daeseda_renewal.domain.user;

import com.experiment.daeseda_renewal.constant.SignupStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    @Override
    public boolean delete(UserDto userDto) {
        Optional<User> user = userRepository.findByUserEmail(userDto.getEmail());

        // 삭제 성공했는지 확인 이후 분기 처리
        if (user.isPresent()) {
            userRepository.deleteById(user.get().getId());
            boolean stillExists = userRepository.existsById(user.get().getId());
            return !stillExists;
        } else {
            return false; // 삭제 대상 없음
        }
    }
}
