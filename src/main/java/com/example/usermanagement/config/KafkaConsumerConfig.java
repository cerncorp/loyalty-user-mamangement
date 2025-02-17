package com.example.usermanagement.config;


import com.example.usermanagement.dto.request.UserRequestDTO;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    @Value(value = "${kafka.topic.user-bulk-create.consumer.group-id}")
    private String userBulkCreateConsumerGroupId;

    @Value(value = "${kafka.topic.user-bulk-create.app-id}")
    private String userBulkCreateAppId;


    // guide: consumer configuration with value type: UserDeserializer
    @Bean
    public ConsumerFactory<String, UserRequestDTO> consumerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        configProps.put(ConsumerConfig.CLIENT_ID_CONFIG, userBulkCreateAppId);
        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, userBulkCreateConsumerGroupId);
        configProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "com.example.usermanagement.util.kafka.UserDeserializer");

        return new DefaultKafkaConsumerFactory<>(configProps);
    }

    // guide: consumer factory for type: UserRequestDTO
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, UserRequestDTO> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, UserRequestDTO> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}
