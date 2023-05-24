package com.wavods.anystore.controllers.dtos.requests;

import java.time.LocalDateTime;

public record ImageRequestDTO(
        Long id,
        String fileName,
        String contentType,
        Long size,
        String url,
        LocalDateTime createdAt,
        Boolean principal
) {
}
