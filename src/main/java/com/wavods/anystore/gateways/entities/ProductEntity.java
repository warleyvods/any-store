package com.wavods.anystore.gateways.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity(name = "`product`")
@Table
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @Size(max = 4000)
    @NotNull
    @Column(columnDefinition = "TEXT")
    private String description;

    @Positive
    private BigDecimal price;

    @Positive
    private Integer quantity;
}
