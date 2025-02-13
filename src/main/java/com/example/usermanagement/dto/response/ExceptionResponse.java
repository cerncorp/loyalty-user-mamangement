package com.example.usermanagement.dto.response;

import java.time.Instant;

public record ExceptionResponse (Instant data, String message, String details) {
}
