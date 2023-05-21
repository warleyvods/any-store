package com.wavods.anystore.usecases;

import com.wavods.anystore.domains.Product;
import com.wavods.anystore.gateways.ProductGateway;
import com.wavods.anystore.gateways.ProductImageGateway;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Optional.ofNullable;

@Component
public record UpdateProduct(ProductGateway productGateway, ProductImageGateway productImageGateway) {

    public Product execute(final Product product) {
        return productGateway.save(product);
    }

    public Product execute(final Product product, final List<Long> productImageIds) {
        ofNullable(productImageIds)
                .filter(productImagesIds -> !productImagesIds.isEmpty())
                .ifPresent(ids -> ids.stream().map(productImageGateway::findById)
                        .forEach(product::addProductImages));

        return productGateway.save(product);
    }
}
