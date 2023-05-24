package com.wavods.anystore.controllers.dtos.requests;

import java.math.BigDecimal;
import java.util.List;

public record ProductPutRequestDTO(
        Long id,
        String name,
        String description,
        BigDecimal price,
        Integer quantity,
        Boolean promo,
        Boolean active,
        List<ImageRequestDTO> productImages
) {
}
