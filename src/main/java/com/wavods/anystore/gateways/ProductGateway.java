package com.wavods.anystore.gateways;


import com.wavods.anystore.domains.Product;
import com.wavods.anystore.exceptions.ProductNotFoundException;
import com.wavods.anystore.gateways.entities.ProductEntity;
import com.wavods.anystore.gateways.entities.ProductImageEntity;
import com.wavods.anystore.gateways.mappers.ProductGatewayMapper;
import com.wavods.anystore.repositories.ProductImageRepository;
import com.wavods.anystore.repositories.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.Optional.ofNullable;

@Slf4j
@Service
public record ProductGateway(ProductRepository productRepository,
                             ProductGatewayMapper productGatewayMapper,
                             ProductImageRepository productImageRepository) {

    private static final String MSG = "product not found!";

    public Product save(final Product product) {
        var productEntity = productGatewayMapper.toEntity(product);
        var productImages = getProductImageEntities(productEntity);
        var managedProductImages = getProductImageEntities(productImages, productEntity);
        productEntity.setProductImages(managedProductImages);

        return productGatewayMapper.toDomain(productRepository.save(productEntity));
    }

    public Page<Product> getAll(final Pageable pageable) {
        return productRepository.findAll(pageable).map(productGatewayMapper::toDomain);
    }

    public Product findById(final Long id) {
        return productGatewayMapper.toDomain(productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(MSG)));
    }


    public Product update(final Product user) {
        return productGatewayMapper.toDomain(productRepository.save(productGatewayMapper.toEntity(user)));
    }

    public void deleteById(final Long id) {
        productRepository.deleteById(id);
    }

    private List<ProductImageEntity> getProductImageEntities(List<ProductImageEntity> productImages, ProductEntity finalProductEntity) {
        return productImages.stream()
                .map(productImage -> {
                    if (productImage.getId() != null) {
                        return productImageRepository.findById(productImage.getId())
                                .map(managedProductImage -> {
                                    managedProductImage.setProduct(finalProductEntity);
                                    return managedProductImage;
                                })
                                .orElse(null);
                    } else {
                        productImage.setProduct(finalProductEntity);
                        return productImage;
                    }
                })
                .filter(Objects::nonNull)
                .toList();
    }

    private static List<ProductImageEntity> getProductImageEntities(final ProductEntity productEntity) {
        return ofNullable(productEntity.getProductImages())
                .map(images -> images.stream()
                        .filter(Objects::nonNull)
                        .toList())
                .orElse(new ArrayList<>());
    }
}
