package com.example.usermanagement.dto.request;
import com.example.usermanagement.model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record UserRequestDTO (
        @NotBlank String username,
        @NotBlank @Email String email,
     @NotNull Date dob
){
    public static User toUser(UserRequestDTO userRequestDTO) {
        User user = new User();
        user.setUserName(userRequestDTO.username);
        user.setDob(userRequestDTO.dob);
        user.setEmail(userRequestDTO.email);
        return user;
    }
}
