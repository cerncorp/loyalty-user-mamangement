package com.example.usermanagement.service;

import com.example.usermanagement.dto.request.UserRequestDTO;
import com.example.usermanagement.model.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {


    List<User> getUsers();

    User getUser(@Positive Long id);

    User createUser(@Valid @NotNull UserRequestDTO userRequestDTO);

    User updateUser(@NotNull Long id, @Valid @NotNull UserRequestDTO userRequestDTO);

    void deleteUser(@Positive @NotNull Long id);
}
