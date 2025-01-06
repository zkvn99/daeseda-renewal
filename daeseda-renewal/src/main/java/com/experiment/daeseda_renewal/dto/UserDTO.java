package com.experiment.daeseda_renewal.dto;

import com.experiment.daeseda_renewal.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserDTO {

    private String userEmail;
    private String userName;
    private String userPassword;

    public static UserDTO fromUser(User user) {
        if(user == null)
            return null;

        return UserDTO.builder()
                .userEmail(user.getEmail())
                .userName(user.getName())
                .build();
    }
}
