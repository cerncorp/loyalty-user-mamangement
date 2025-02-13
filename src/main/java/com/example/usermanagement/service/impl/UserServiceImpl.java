package com.example.usermanagement.service.impl;

import com.example.usermanagement.dto.request.UserRequestDTO;
import com.example.usermanagement.exception.ResourceNotFoundException;
import com.example.usermanagement.model.User;
import com.example.usermanagement.repository.UserRepository;
import com.example.usermanagement.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * @return
     */
    @Override
    public List<User> getUsers() {
        // todo: implement pagination and sorting
        Pageable firstPageWithTenElements = PageRequest.of(0, 10);

        Page<User> allUsers = userRepository.findAll(firstPageWithTenElements);
        return allUsers.toList();
    }

    /**
     * @param id
     * @return
     */
    @Override
    public User getUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " not found"));
        return user;
    }

    /**
     * @param userRequestDTO
     * @return
     */
    @Transactional
    @Override
    public User createUser(UserRequestDTO userRequestDTO) {
        User user = UserRequestDTO.toUser(userRequestDTO);

        User userResult = userRepository.save(user);
        return userResult;
    }

    /**
     * @param userRequestDTO
     * @return
     */
    @Override
    public User updateUser(Long id, UserRequestDTO userRequestDTO) {
        User foundUser = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " not found"));

        User user = UserRequestDTO.toUser(userRequestDTO);
        user.setId(foundUser.getId());

        User userResult = userRepository.save(user);
        return userResult;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public void deleteUser(Long id) {
        User foundUser = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " not found"));
        userRepository.delete(foundUser);
    }
}
