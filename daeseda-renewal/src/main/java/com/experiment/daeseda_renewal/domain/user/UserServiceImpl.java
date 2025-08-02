package com.experiment.daeseda_renewal.domain.user;

import com.experiment.daeseda_renewal.constant.ErrorCode;
import com.experiment.daeseda_renewal.global.exception.BusinessException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  public void signUp(UserDto userDTO) {

    if (userRepository.existsByEmail(userDTO.getEmail())) {
      throw new BusinessException(ErrorCode.DUPLICATE_EMAIL);
    }

    User user = User.builder()
                    .name(userDTO.getName())
                    .email(userDTO.getEmail())
                    .password(passwordEncoder.encode(userDTO.getPassword()))
                    .build();

    userRepository.save(user);
  }

  @Override
  public void signOut() {
  }

  @Override
  public UserDto login(UserDto userDTO) {
    User user = userRepository.findByEmail(userDTO.getEmail())
                              .orElseThrow(
                                  () -> new BusinessException(ErrorCode.LOGIN_VALID_FAILED));
    if (!passwordEncoder.matches(userDTO.getPassword(), user.getPassword())) {
      throw new BusinessException(ErrorCode.LOGIN_VALID_FAILED);
    }

    return UserDto.fromUser(user);
  }

  @Override
  public String findEmailByName(String name) {
    User user = Optional.ofNullable(userRepository.findByName(name))
                        .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

    return user.getEmail();
  }

  @Override
  public boolean isEmailDuplicate(String email) {
    return userRepository.existsByEmail(email);
  }

  @Override
  public void delete(UserDto userDto) {
    User user = userRepository.findByEmail(userDto.getEmail())
                              .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
    userRepository.deleteById(user.getId());
  }
}
