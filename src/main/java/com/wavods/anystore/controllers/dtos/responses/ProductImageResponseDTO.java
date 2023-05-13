package com.wavods.anystore.controllers.dtos.responses;

public record ProductImageResponseDTO(
        Long id,
        String fileName,
        String contentType,
        Long size,
        String url,
        String createdAt
) {
}
