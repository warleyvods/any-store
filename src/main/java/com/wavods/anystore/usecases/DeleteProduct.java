package com.wavods.anystore.usecases;

import com.wavods.anystore.gateways.ProductGateway;
import org.springframework.stereotype.Component;

@Component
public record DeleteProduct(ProductGateway productGateway) {

    public void execute(final Long id) {
        productGateway.deleteById(id);
    }
}
