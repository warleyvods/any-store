package com.wavods.anystore.controllers;

import com.wavods.anystore.controllers.dtos.responses.ProductImageResponseDTO;
import com.wavods.anystore.controllers.mappers.ProductImageMapper;
import com.wavods.anystore.usecases.CreateProductImage;
import com.wavods.anystore.usecases.DeleteProductImage;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Tag(name = "Product Image Controller")
@RestController
@RequiredArgsConstructor
@RequestMapping("v1/product-image")
@SecurityRequirement(name = "bearerAuth")
public class ProductImageController {

    private final ProductImageMapper productImageMapper;
    private final CreateProductImage createProductImage;
    private final DeleteProductImage deleteProductImage;

    @PostMapping(value = "/upload")
    public ProductImageResponseDTO imageUpload(final MultipartFile image) throws IOException {
        return productImageMapper.toDto(createProductImage.execute(image));
    }

    @DeleteMapping
    public void deleteFile(@RequestParam List<Long> imageIds) {
        deleteProductImage.execute(imageIds);
    }
}
