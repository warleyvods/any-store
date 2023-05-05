package com.wavods.anystore.gateways.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

import static jakarta.persistence.GenerationType.*;

@Getter
@Setter
@Table
@Entity(name = "`product`")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    @Size(max = 4000)
    @Column(columnDefinition = "TEXT")
    private String description;

    @NotNull
    @Positive
    private BigDecimal price;

    @NotNull
    @Positive
    private Integer quantity;

    private Boolean promo;

    private BigDecimal promoPrice;

    private String urlImage;

    private Boolean active;

}
