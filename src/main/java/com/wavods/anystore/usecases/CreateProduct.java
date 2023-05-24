package com.wavods.anystore.usecases;

import com.wavods.anystore.domains.Product;
import com.wavods.anystore.gateways.ProductGateway;
import com.wavods.anystore.gateways.ProductImageGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public record CreateProduct(ProductGateway productGateway, ProductImageGateway productImageGateway) {

    public Product execute(final Product product) {
        return productGateway.save(product);
    }
}
