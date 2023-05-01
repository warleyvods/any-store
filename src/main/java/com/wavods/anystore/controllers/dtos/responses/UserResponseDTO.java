package com.wavods.anystore.controllers.dtos.responses;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record UserResponseDTO(
        Long id,
        String name,
        String login,
        String email,
        Boolean admin,
        Boolean active,
        LocalDate createdAt,
        LocalDateTime lastAccess
) {
}
