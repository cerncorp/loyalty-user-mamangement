package com.example.usermanagement.service;

public interface BulkUserProducerService {

    void bulkUsersAndKafkaPublish(int numUsers);
    void bulkUsersAndSQSPublish(int numUsers);
}
