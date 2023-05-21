package com.wavods.anystore.controllers;

import com.wavods.anystore.controllers.dtos.requests.ProductPostRequestDTO;
import com.wavods.anystore.controllers.dtos.requests.ProductPutRequestDTO;
import com.wavods.anystore.controllers.dtos.responses.ProductImageResponseDTO;
import com.wavods.anystore.controllers.dtos.responses.ProductResponseDTO;
import com.wavods.anystore.controllers.mappers.ProductImageMapper;
import com.wavods.anystore.controllers.mappers.ProductMapper;
import com.wavods.anystore.usecases.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
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
    private final UpdateProduct updateProduct;

    @PostMapping
    public ProductResponseDTO save(@RequestBody final ProductPostRequestDTO productPostRequestDTO) {
        return productMapper.toDto(createProduct.execute(productMapper.toDomain(productPostRequestDTO)));
    }

    @PutMapping
    public ProductResponseDTO update(@RequestBody @Valid final ProductPutRequestDTO productPutRequestDTO) {
        final var product = findProduct.byId(productPutRequestDTO.id());
        final var productDomain = updateProduct.execute(productMapper.updateProductFromDTO(productPutRequestDTO, product));
        return productMapper.toDto(productDomain);
    }

    @GetMapping("/public")
    public Page<ProductResponseDTO> getAll(final Pageable pageable) {
        return findProduct.byId(pageable).map(productMapper::toDto);
    }

    @GetMapping("/public/{id}")
    public ProductResponseDTO findById(@PathVariable final Long id) {
        return productMapper.toDto(findProduct.byId(id));
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable final Long id) {
        deleteProduct.execute(id);
    }

    //TODO criar um controller para imagens
    @PostMapping(value = "/image/upload")
    public ProductImageResponseDTO imageUpload(final MultipartFile image) throws IOException {
        return productImageMapper.toDto(createProductImage.execute(image));
    }
}
