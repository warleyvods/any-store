package com.wavods.anystore.gateways;


import com.wavods.anystore.domains.Product;
import com.wavods.anystore.exceptions.ProductNotFoundException;
import com.wavods.anystore.gateways.mappers.ProductGatewayMapper;
import com.wavods.anystore.repositories.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public record ProductGateway(ProductRepository productRepository, ProductGatewayMapper productGatewayMapper) {

    private static final String MSG = "product not found!";

    public Product save(final Product product) {
        return productGatewayMapper.toDomain(productRepository.save(productGatewayMapper.toEntity(product)));
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
