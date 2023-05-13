package com.wavods.anystore.domains;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ProductImage {

    private Long id;
    private String fileName;
    private String contentType;
    private Long size;
    private String urlImage;
    private LocalDate createdAt;

}
