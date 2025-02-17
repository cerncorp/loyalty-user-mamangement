package com.example.usermanagement.job;

import com.example.usermanagement.dto.request.UserRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class KafkaConsumerService {

    @KafkaListener(
            topics = "${kafka.topic.user-bulk-create}",
            groupId = "group_id",
            concurrency = "${kafka.topic.user-bulk-create.consumer.concurrency}")
    public void consume(UserRequestDTO message) {
        log.info("Message received: " + message);
    }
}