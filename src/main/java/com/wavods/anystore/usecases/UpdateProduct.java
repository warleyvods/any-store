package com.wavods.anystore.usecases;

import com.wavods.anystore.domains.Product;
import com.wavods.anystore.gateways.ProductGateway;
import com.wavods.anystore.gateways.ProductImageGateway;
import org.springframework.stereotype.Component;

@Component
public record UpdateProduct(ProductGateway productGateway, ProductImageGateway productImageGateway) {

    public Product execute(final Product product) {
        return productGateway.save(product);
    }
}
