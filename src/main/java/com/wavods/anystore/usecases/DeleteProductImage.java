package com.wavods.anystore.usecases;

import com.wavods.anystore.gateways.ProductImageGateway;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public record DeleteProductImage(ProductImageGateway productImageGateway) {

    public void execute(final List<Long> imagesIds) {
        productImageGateway.deleteAll(imagesIds);
    }
}
