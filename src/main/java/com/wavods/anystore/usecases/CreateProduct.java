package com.wavods.anystore.usecases;

import com.wavods.anystore.domains.Product;
import com.wavods.anystore.gateways.ProductGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateProduct {

    private final ProductGateway productGateway;

    public Product execute(final Product product) {
       return productGateway.save(product);
    }
}
