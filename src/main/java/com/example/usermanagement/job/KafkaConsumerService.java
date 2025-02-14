package com.example.usermanagement.job;

import com.example.usermanagement.dto.request.UserRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class KafkaConsumerService {

//    Map<String, Set<String>> consumedRecords = new ConcurrentHashMap<>();

//    @KafkaListener(topics = "bulk-create-user-topic", groupId = "group-1")
//    public void consumer1(ConsumerRecord<?, ?> consumerRecord) {
//        trackConsumedPartitions("consumer-1", consumerRecord.partition());
//    }
//
//    private void trackConsumedPartitions(String consumerName, int partitionNumber) {
//        consumedRecords.computeIfAbsent(consumerName, k -> new HashSet<>());
//        consumedRecords.computeIfPresent(consumerName, (k, v) -> {
//            v.add(String.valueOf(partitionNumber));
//            return v;
//        });
//    }

    @KafkaListener(topics = "bulk-create-user-topic", groupId = "group_id")
    public void consume(UserRequestDTO message) {
        log.info("Message received: " + message);
    }
}