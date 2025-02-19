package com.example.usermanagement.job;

import com.example.usermanagement.dto.request.UserRequestDTO;
import com.example.usermanagement.model.User;
import com.example.usermanagement.service.UserService;
import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Slf4j
@Service
public class SQSListenerService {

    @Autowired
    private UserService userService;

    @SqsListener("${cloud.aws.sqs.topic.bulk-user}")
    public void listen(Message<?> message) {

        log.info("Message received on listen method at {}", OffsetDateTime.now());
        UserRequestDTO userRequestDTO = (UserRequestDTO) message.getPayload();
        log.info("userRequestDTO received: {} at {}", userRequestDTO, OffsetDateTime.now());

        User user = userService.createUser(userRequestDTO);
        log.info("User {} created successfully.", user.getUserName());

    }
}
