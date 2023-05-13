package com.wavods.anystore.controllers.dtos.responses;

import java.math.BigDecimal;
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
        List<ProductImageResponseDTO> productImages
) {
}
