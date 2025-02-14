package com.example.usermanagement.service.impl;

import com.example.usermanagement.dto.request.UserRequestDTO;
import com.example.usermanagement.model.User;
import com.example.usermanagement.service.BulkUserProducerService;
import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class BulkUserProducerServiceImpl implements BulkUserProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public BulkUserProducerServiceImpl(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void bulkUsersAndPublish(int numUsers) {
        log.info("bulkUsersAndPublish called with numUsers {}", numUsers);

        Faker faker = new Faker();

        new Random().ints(100, 999)
                .limit(numUsers)
                .forEach(tmp -> {
                    // todo: test this
                    UserRequestDTO userRequestDTO = new UserRequestDTO(
                            faker.name().username(),
                            faker.internet().emailAddress(),
                            faker.date().past(30, TimeUnit.DAYS)
                    );

                    kafkaTemplate.send("bulk-create-user-topic", "test-value-" + tmp);
                });
    }
}
