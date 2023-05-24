package com.wavods.anystore.gateways;

import com.wavods.anystore.domains.ProductImage;
import com.wavods.anystore.gateways.mappers.ProductGatewayMapper;
import com.wavods.anystore.gateways.mappers.ProductImageGatewayMapper;
import com.wavods.anystore.repositories.ProductImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductImageGateway {

    private final ImageGateway imageGateway;
    private final ProductImageGatewayMapper productImageGatewayMapper;
    private final ProductImageRepository productImageRepository;

    public ProductImage save(final MultipartFile image) throws IOException {
        final var productImageEntity = productImageGatewayMapper.toEntity(imageGateway.save(image));
        return productImageGatewayMapper.toDomain(productImageRepository.save(productImageEntity));
    }

    public ProductImage findById(final Long id) {
        return productImageGatewayMapper.toDomain(productImageRepository.findById(id).orElse(null));
    }

    public List<ProductImage> findAllById(final List<Long> imageIds) {
        return productImageRepository.findAllById(imageIds).stream()
                .map(productImageGatewayMapper::toDomain)
                .toList();
    }
}
