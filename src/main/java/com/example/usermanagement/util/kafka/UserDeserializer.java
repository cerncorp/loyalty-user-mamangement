package com.example.usermanagement.util.kafka;

import com.example.usermanagement.dto.request.UserRequestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;

import java.nio.ByteBuffer;
import java.util.Map;

@Slf4j
public class UserDeserializer implements Deserializer<UserRequestDTO> {
    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * @param configs
     * @param isKey
     */
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Deserializer.super.configure(configs, isKey);
    }

    /**
     * @param s
     * @param data
     * @return
     */
    @Override
    public UserRequestDTO deserialize(String s, byte[] data) {
        try {
            if (data == null){
                log.info("Null received at deserializing");
                return null;
            }
//            log.info("Deserialized...");
            return objectMapper.readValue(new String(data, "UTF-8"), UserRequestDTO.class);
        } catch (Exception e) {
            throw new SerializationException("Error when deserializing byte[] to UserRequestDTO");
        }
    }

    /**
     * @param topic
     * @param headers
     * @param data
     * @return
     */
    @Override
    public UserRequestDTO deserialize(String topic, Headers headers, byte[] data) {
        return Deserializer.super.deserialize(topic, headers, data);
    }

    /**
     * @param topic
     * @param headers
     * @param data
     * @return
     */
    @Override
    public UserRequestDTO deserialize(String topic, Headers headers, ByteBuffer data) {
        return Deserializer.super.deserialize(topic, headers, data);
    }

    /**
     *
     */
    @Override
    public void close() {
        Deserializer.super.close();
    }
}
