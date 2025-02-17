package com.example.usermanagement.job;

import com.example.usermanagement.dto.request.UserRequestDTO;
import com.example.usermanagement.model.User;
import com.example.usermanagement.service.UserService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class KafkaConsumerService {

    @Autowired
    private UserService userService;

    @Transactional
    @KafkaListener(
            topics = "${kafka.topic.user-bulk-create}",
            groupId = "${kafka.topic.user-bulk-create.consumer.group-id}",
            concurrency = "${kafka.topic.user-bulk-create.consumer.concurrency}")
    public void consume(@Valid UserRequestDTO userRequestDTO) {
        log.info("UserRequestDTO received: {}", userRequestDTO.username());

        User user = userService.createUser(userRequestDTO);
        log.info("User {} created successfully.", user.getUserName());
    }
}