package com.wavods.anystore.gateways.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
    private String urlImage;

    @JoinColumn
    @ManyToOne
    private ProductEntity product;

}
