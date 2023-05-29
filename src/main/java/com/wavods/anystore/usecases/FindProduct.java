package com.wavods.anystore.usecases;

import com.wavods.anystore.domains.Product;
import com.wavods.anystore.gateways.ProductGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public record FindProduct(ProductGateway productGateway) {

    public Page<Product> byId(final Pageable pageable) {
        return productGateway.getAll(pageable);
    }

    public Page<Product> byIdWithFilters(final Boolean status, final String keyword, final Pageable pageable) {
        return productGateway.getAllWithFilters(status, keyword, pageable);
    }

    public Product byId(final Long id) {
        return productGateway.findById(id);
    }
}
