package com.wavods.anystore.gateways;


import com.wavods.anystore.domains.Product;
import com.wavods.anystore.exceptions.ProductNotFoundException;
import com.wavods.anystore.gateways.entities.ProductEntity;
import com.wavods.anystore.gateways.entities.ProductImageEntity;
import com.wavods.anystore.gateways.mappers.ProductGatewayMapper;
import com.wavods.anystore.repositories.ProductRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.util.Comparator.comparing;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductGateway {

    private final ProductRepository productRepository;
    private final ProductGatewayMapper productGatewayMapper;
    private final EntityManager entityManager;

    private static final String MSG = "product not found!";

    @Transactional
    public Product save(final Product product) {
        return productGatewayMapper.toDomain(entityManager.merge(attachImage(product)));
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

    private ProductEntity attachImage(Product product) {
        final ProductEntity entity = productGatewayMapper.toEntity(product);

        if (entity.getProductImages() != null) {
            principalImageValidation(entity);
            for (ProductImageEntity image : entity.getProductImages()) {
                image.setProduct(entity);

                if (entityManager.contains(image)) {
                    entityManager.merge(image);
                }
            }
        }
        return entity;
    }

    private static void principalImageValidation(final ProductEntity entity) {


        if (entity.getProductImages().stream().noneMatch(ProductImageEntity::getPrincipal)) {
            entity.getProductImages().stream()
                    .min(comparing(ProductImageEntity::getCreatedAt))
                    .ifPresent(image -> image.setPrincipal(true));
        }

        if (entity.getProductImages().stream().filter(ProductImageEntity::getPrincipal).count() > 1) {
            entity.getProductImages().stream()
                    .filter(ProductImageEntity::getPrincipal)
                    .max(comparing(ProductImageEntity::getCreatedAt))
                    .ifPresent(image -> image.setPrincipal(false));
        }
    }
}
