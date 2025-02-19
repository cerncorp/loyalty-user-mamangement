package com.example.usermanagement.service.impl;

import com.example.usermanagement.dto.request.UserRequestDTO;
import com.example.usermanagement.exception.ResourceNotFoundException;
import com.example.usermanagement.model.User;
import com.example.usermanagement.repository.UserRepository;
import com.example.usermanagement.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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
    public List<User> getUsers(Pageable pageable) {
        Page<User> allUsers = userRepository.findAll(pageable);
        return allUsers.toList();
    }

    /**
     * @param id
     * @return
     */
    @Override
    @Cacheable(value="userCache", key="#root.methodName + ':' + #id + ':' + 'id'", unless = "#result == null") //, unless = "#result == null"
    public User getUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " not found"));
        return user;
    }

    /**
     * @param userRequestDTO
     * @return
     */
    @Override
    @Transactional
    @CachePut(value="userCache", key="#root.methodName + ':'  + #result.getId() + ':'  + 'id'", unless = "#result == null")
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
    @Transactional
    @CachePut(value="userCache", key="#root.methodName + ':'  + #result.getId() + ':'  + 'id'")
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
    @Transactional
    @CacheEvict(value="userCache", key="#root.methodName + ':'  + #id + ':'  + 'id'")
    public void deleteUser(Long id) {
        User foundUser = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " not found"));
        userRepository.delete(foundUser);
    }

    /**
     * @param username
     * @return
     */
    @Override
    @Cacheable(value="userCache", key="#root.methodName + ':'  + #username + ':'  + 'username'", unless = "#result == null")
    public User getUserByName(String username) {
        User user = userRepository.findByUserName(username).orElseThrow(() -> new ResourceNotFoundException("User with username " + username + " not found"));
        return user;
    }
}
