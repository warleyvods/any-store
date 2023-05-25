package com.wavods.anystore.controllers.dtos.requests;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record ProductPostRequestDTO(
        String name,
        String description,
        BigDecimal price,
        Integer quantity,
        Boolean promo,
        Boolean active,
        Boolean archived,
        LocalDateTime createdAt,
        List<ImageRequestDTO> productImages
) {
}
