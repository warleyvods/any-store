package com.wavods.anystore.controllers;

import com.wavods.anystore.controllers.dtos.requests.ProductRequestDTO;
import com.wavods.anystore.controllers.dtos.responses.ProductResponseDTO;
import com.wavods.anystore.controllers.mappers.ProductMapper;
import com.wavods.anystore.usecases.CreateProduct;
import com.wavods.anystore.usecases.FindProduct;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Product Controller")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
@RequestMapping("v1/products")
@RestController
public class ProductController {

    private final CreateProduct createProduct;
    private final ProductMapper productMapper;
    private final FindProduct findProduct;

    @PostMapping
    public ProductResponseDTO save(@RequestBody final ProductRequestDTO productRequestDTO) {
        return productMapper.toDto(createProduct.execute(productMapper.toDomain(productRequestDTO)));
    }

    @GetMapping("/public")
    public Page<ProductResponseDTO> getAll(final Pageable pageable) {
        return findProduct.execute(pageable).map(productMapper::toDto);
    }
}
