package com.wavods.anystore.controllers.dtos.requests;

import java.math.BigDecimal;

public record ProductRequestDTO(
        String name,
        String description,
        BigDecimal price,
        Integer quantity,
        Boolean promo,
        BigDecimal promoPrice, //todo
        Boolean active
) {
}
