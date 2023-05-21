package com.wavods.anystore.gateways.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity(name = "product_image")
public class ProductImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;
    private String contentType;
    private Long size;
    private String url;
    private LocalDateTime createdAt;
    private Boolean principal;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

}
