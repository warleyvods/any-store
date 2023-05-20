package com.wavods.anystore.domains;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ProductImage {

    private Long id;
    private String fileName;
    private String contentType;
    private Long size;
    private String url;
    private LocalDateTime createdAt;
    private Boolean principal;

    public ProductImage(String fileName, String contentType, Long size, String urlImage, LocalDateTime createdAt) {
        this.fileName = fileName;
        this.contentType = contentType;
        this.size = size;
        this.url = urlImage;
        this.createdAt = createdAt;
    }
}
