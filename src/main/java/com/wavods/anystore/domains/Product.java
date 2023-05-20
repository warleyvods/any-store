package com.wavods.anystore.domains;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
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
    private List<ProductImage> productImages;

    public Boolean isPromo() {
        return this.promo;
    }

    public Boolean isActive() {
        return this.active;
    }

    public void activate() {
        this.active = true;
    }

    public void deactivate() {
        this.active = false;
    }

    public void activatePromo() {
        this.promo = true;
    }

    public void deactivatePromo() {
        this.promo = false;
    }

    public void addProductImages(final ProductImage productImage) {
        this.productImages.add(productImage);
    }
}
