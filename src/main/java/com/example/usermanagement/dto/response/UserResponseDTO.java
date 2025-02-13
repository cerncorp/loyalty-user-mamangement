package com.example.usermanagement.dto.response;

import com.example.usermanagement.model.User;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserResponseDTO {
    private Long id;
    private String username;
    private Integer age;

    public static UserResponseDTO fromUser(User user) {
        UserResponseDTO userResponseDTO = new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getAge()
        );
        return userResponseDTO;
    }

    public static List<UserResponseDTO> fromUsers(@NotNull List<User> users) {
        return users.stream().map(UserResponseDTO::fromUser).toList();
    }
}
