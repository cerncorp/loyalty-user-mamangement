package com.example.usermanagement.controller;

import com.example.usermanagement.dto.request.UserRequestDTO;
import com.example.usermanagement.dto.response.UserResponseDTO;
import com.example.usermanagement.model.User;
import com.example.usermanagement.service.BulkUserProducerService;
import com.example.usermanagement.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;


    @Autowired
    private BulkUserProducerService bulkUserProducerService;

    @GetMapping("/user")
    public ResponseEntity<List<UserResponseDTO>> getUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
//            @RequestParam(defaultValue = "DESC") String sortDirection
        log.info("getUsers called");
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        List<User> users = userService.getUsers(pageable);

        return ResponseEntity.ok(UserResponseDTO.fromUsers(users));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable @Positive Long id) {
        log.info("getUser called with id {}", id);
        User user = userService.getUser(id);
        return ResponseEntity.ok(UserResponseDTO.fromUser(user));
    }

    @PostMapping("/user")
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody @Valid @NotNull UserRequestDTO userRequestDTO) {
        log.info("createUser called with username {}", userRequestDTO.username());
        User user = userService.createUser(userRequestDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(UserResponseDTO.fromUser(user));
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(
            @PathVariable @Positive @NotNull Long id,
            @RequestBody @Valid @NotNull UserRequestDTO userRequestDTO

    ) {
        log.info("updateUser called with username {}", userRequestDTO.username());
        User user = userService.updateUser(id, userRequestDTO);

        return ResponseEntity.ok(UserResponseDTO.fromUser(user));
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(
            @PathVariable @Positive @NotNull Long id
    ) {
        log.info("deleteUser called with id {}", id);
        userService.deleteUser(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/send")
    public String sendMessage(@RequestParam String message) {
        bulkUserProducerService.bulkUsersAndPublish(50);
        return "Message sent successfully";
    }
}
