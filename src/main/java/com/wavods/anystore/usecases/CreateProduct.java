package com.wavods.anystore.usecases;

import com.wavods.anystore.domains.Product;
import com.wavods.anystore.gateways.ProductGateway;
import org.springframework.stereotype.Component;

@Component
public record CreateProduct(ProductGateway productGateway) {

    public Product execute(final Product product) {
       return productGateway.save(product);
    }
}
