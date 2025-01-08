package com.experiment.daeseda_renewal.dto;

import com.experiment.daeseda_renewal.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserDTO {

    private String email;
    private String name;
    private String password;

    public static UserDTO fromUser(User user) {
        if(user == null)
            return null;

        return UserDTO.builder()
                .email(user.getEmail())
                .name(user.getName())
                .build();
    }
}
