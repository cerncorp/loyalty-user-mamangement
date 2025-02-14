package com.example.usermanagement.util.kafka;

import com.example.usermanagement.dto.request.UserRequestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

@Slf4j
public class UserSerializer implements Serializer<UserRequestDTO> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * @param configs
     * @param isKey
     */
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Serializer.super.configure(configs, isKey);
    }

    /**
     * @param s
     * @param userRequestDTO
     * @return
     */
    @Override
    public byte[] serialize(String s, UserRequestDTO userRequestDTO) {
        try {
            if (userRequestDTO == null){
                log.info("Null received at serializing");
                return null;
            }
//            log.info("Serializing...");
            return objectMapper.writeValueAsBytes(userRequestDTO);
        } catch (Exception e) {
            throw new SerializationException("Error when serializing UserRequestDTO to byte[]");
        }
    }

    /**
     * @param topic
     * @param headers
     * @param data
     * @return
     */
    @Override
    public byte[] serialize(String topic, Headers headers, UserRequestDTO data) {
        return Serializer.super.serialize(topic, headers, data);
    }

    /**
     *
     */
    @Override
    public void close() {
        Serializer.super.close();
    }
}
