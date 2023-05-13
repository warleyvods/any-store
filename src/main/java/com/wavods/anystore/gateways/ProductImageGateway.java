package com.wavods.anystore.gateways;

import com.wavods.anystore.domains.ProductImage;
import com.wavods.anystore.gateways.entities.ProductImageEntity;
import com.wavods.anystore.gateways.mappers.ProductImageGatewayMapper;
import com.wavods.anystore.repositories.ProductImageRepository;
import com.wavods.anystore.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductImageGateway {

    private final ProductRepository productRepository;
    private final ImageGateway imageGateway;
    private final ProductImageGatewayMapper productImageGatewayMapper;
    private final ProductImageRepository productImageRepository;

    public ProductImage save(final MultipartFile image, final Long productId) throws IOException {
        final var product = productRepository.findById(productId);
        imageGateway.imageValidation(image);
        if (product.isPresent()) {
            final ProductImageEntity productImageEntity = productImageGatewayMapper.toEntity(imageGateway.save(image), product.get());
            return productImageGatewayMapper.toDomain(productImageRepository.save(productImageEntity));
        }
        log.info("product not found!");
        return null;
    }
}
