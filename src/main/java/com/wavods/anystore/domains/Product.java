package com.wavods.anystore.domains;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class Product {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer quantity;
    private Boolean promo;
    private BigDecimal promoPrice;
    private Boolean active;
    private Boolean archived;
    private LocalDateTime createdAt;
    private List<ProductImage> productImages;

}
