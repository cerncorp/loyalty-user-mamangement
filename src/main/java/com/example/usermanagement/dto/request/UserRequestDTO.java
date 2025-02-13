package com.example.usermanagement.dto.request;
import com.example.usermanagement.model.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record UserRequestDTO (
     @NotBlank String username,
     @NotNull @PositiveOrZero Integer age
){
    public static User toUser(UserRequestDTO userRequestDTO) {
        User user = new User(
                null,
                userRequestDTO.username,
                userRequestDTO.age
        );
        return user;
    }
}
