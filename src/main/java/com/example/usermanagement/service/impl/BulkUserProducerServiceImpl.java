package com.example.usermanagement.service.impl;

import com.example.usermanagement.dto.request.UserRequestDTO;
import com.example.usermanagement.model.User;
import com.example.usermanagement.service.BulkUserProducerService;
import com.github.javafaker.Faker;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class BulkUserProducerServiceImpl implements BulkUserProducerService {

    private final KafkaTemplate<String, UserRequestDTO> kafkaTemplate;
    private final SqsTemplate sqsTemplate;

    @Value(value = "${kafka.topic.user-bulk-create}")
    private String bulkCreateUserTopic;
    @Value(value = "${cloud.aws.sqs.topic.bulk-user}")
    private String bulkCreateUserQueueName;



    public BulkUserProducerServiceImpl(KafkaTemplate<String, UserRequestDTO> kafkaTemplate,
                                       SqsTemplate sqsTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        this.sqsTemplate = sqsTemplate;
    }

    public void bulkUsersAndKafkaPublish(int numUsers) {
        log.info("bulkUsersAndKafkaPublish called with numUsers {}", numUsers);

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

                    kafkaTemplate.send(bulkCreateUserTopic, userRequestDTO);
                });
    }

    public void bulkUsersAndSQSPublish(int numUsers) {
        log.info("bulkUsersAndSQSPublish called with numUsers {}", numUsers);

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

                    sqsTemplate.send(bulkCreateUserQueueName, userRequestDTO);
                });
    }
}
