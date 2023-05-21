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
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Slf4j
@Service
public record ProductGateway(ProductRepository productRepository, ProductGatewayMapper productGatewayMapper, ProductImageRepository productImageRepository) {

    private static final String MSG = "product not found!";

    public Product save(final Product product) {
        ProductEntity productEntity = productGatewayMapper.toEntity(product);
        List<ProductImageEntity> productImages = Optional.ofNullable(productEntity.getProductImages())
                .map(images -> images.stream()
                        .filter(Objects::nonNull)
                        .toList())
                .orElse(new ArrayList<>());

        List<ProductImageEntity> managedProductImages = new ArrayList<>();
        for (ProductImageEntity productImage : productImages) {
            if (productImage.getId() != null) {
                // Load the existing ProductImageEntity from the database
                ProductImageEntity managedProductImage = productImageRepository.findById(productImage.getId()).orElse(null);
                if (managedProductImage != null) {
                    // Update the bidirectional association
                    managedProductImage.setProduct(productEntity);
                    managedProductImages.add(managedProductImage);
                }
            } else {
                productImage.setProduct(productEntity);
                managedProductImages.add(productImage);
            }
        }
        productEntity.setProductImages(managedProductImages);

        productEntity = productRepository.save(productEntity);

        return productGatewayMapper.toDomain(productEntity);
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
}
