package com.wavods.anystore.controllers;

import com.wavods.anystore.controllers.dtos.requests.ProductRequestDTO;
import com.wavods.anystore.controllers.dtos.responses.ProductImageResponseDTO;
import com.wavods.anystore.controllers.dtos.responses.ProductResponseDTO;
import com.wavods.anystore.controllers.mappers.ProductImageMapper;
import com.wavods.anystore.controllers.mappers.ProductMapper;
import com.wavods.anystore.usecases.CreateProduct;
import com.wavods.anystore.usecases.CreateProductImage;
import com.wavods.anystore.usecases.DeleteProduct;
import com.wavods.anystore.usecases.FindProduct;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Tag(name = "Product Controller")
@RestController
@RequiredArgsConstructor
@RequestMapping("v1/products")
@SecurityRequirement(name = "bearerAuth")
public class ProductController {

    private final CreateProduct createProduct;
    private final ProductMapper productMapper;
    private final ProductImageMapper productImageMapper;
    private final FindProduct findProduct;
    private final DeleteProduct deleteProduct;
    private final CreateProductImage createProductImage;

    @PostMapping
    public ProductResponseDTO save(@RequestBody final ProductRequestDTO productRequestDTO) {
        return productMapper.toDto(createProduct.execute(productMapper.toDomain(productRequestDTO)));
    }

    @GetMapping("/public")
    public Page<ProductResponseDTO> getAll(final Pageable pageable) {
        return findProduct.execute(pageable).map(productMapper::toDto);
    }

    @GetMapping("/public/{id}")
    public ProductResponseDTO findById(@PathVariable final Long id) {
        return productMapper.toDto(findProduct.execute(id));
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable final Long id) {
        deleteProduct.execute(id);
    }

    //todo: desabilitado momentaneamente.
    @PostMapping(value = "/image/upload-product-association")
    private ProductImageResponseDTO imageWithProductUpload(final MultipartFile image, @RequestParam final Long productId) throws IOException {
        return productImageMapper.toDto(createProductImage.execute(image, productId));
    }

    @PostMapping(value = "/image/upload")
    public ProductImageResponseDTO imageUpload(final MultipartFile image) throws IOException {
        return productImageMapper.toDto(createProductImage.execute(image));
    }
}
