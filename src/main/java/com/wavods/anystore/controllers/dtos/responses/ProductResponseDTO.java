package com.wavods.anystore.controllers.dtos.responses;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record ProductResponseDTO(
        Long id,
        String name,
        String description,
        BigDecimal price,
        Integer quantity,
        Boolean promo,
        BigDecimal promoPrice,
        Boolean active,
        Boolean archived,
        LocalDateTime createdAt,
        List<ProductImageResponseDTO> productImages
) {
}
