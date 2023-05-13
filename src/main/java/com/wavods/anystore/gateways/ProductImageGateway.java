package com.wavods.anystore.gateways;

import com.wavods.anystore.domains.Product;
import com.wavods.anystore.domains.ProductImage;
import com.wavods.anystore.gateways.entities.ProductEntity;
import com.wavods.anystore.gateways.mappers.ProductGatewayMapper;
import com.wavods.anystore.gateways.mappers.ProductImageGatewayMapper;
import com.wavods.anystore.repositories.ProductImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductImageGateway {

    private final ImageGateway imageGateway;
    private final ProductImageGatewayMapper productImageGatewayMapper;
    private final ProductGatewayMapper productGatewayMapper;
    private final ProductImageRepository productImageRepository;

    public ProductImage save(final MultipartFile image, final Product product) throws IOException {
        final var productEntity = productGatewayMapper.toEntity(product);
        final var productImageEntity = productImageGatewayMapper.toEntity(imageGateway.save(image), productEntity);
        return productImageGatewayMapper.toDomain(productImageRepository.save(productImageEntity));
    }
}
