package com.wavods.anystore.usecases;

import com.wavods.anystore.domains.Product;
import com.wavods.anystore.gateways.ProductGateway;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public record FindProduct(ProductGateway productGateway) {

    public Page<Product> byId(final Pageable pageable) {
        return productGateway.getAll(pageable);
    }

    public Product byId(final Long id) {
        return productGateway.findById(id);
    }
}
