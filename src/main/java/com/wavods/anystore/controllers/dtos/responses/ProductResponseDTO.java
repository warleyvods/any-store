package com.wavods.anystore.controllers.dtos.responses;

import java.math.BigDecimal;

public record ProductResponseDTO(
        Long id,
        String name,
        String description,
        BigDecimal price,
        Integer quantity,
        Boolean promo,
        BigDecimal promoPrice,
        String urlImage,
        Boolean active
) {
}
