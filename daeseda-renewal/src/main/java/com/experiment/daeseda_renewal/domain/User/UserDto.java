package com.experiment.daeseda_renewal.domain.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserDto {

    private Long id;
    private String email;
    private String name;
    private String password;

    public static UserDto fromUser(User user) {
        if(user == null)
            return null;

        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .build();
    }
}
