package com.wavods.anystore.usecases;

import com.wavods.anystore.domains.ProductImage;
import com.wavods.anystore.gateways.ProductGateway;
import com.wavods.anystore.gateways.ProductImageGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public record DeleteProduct(ProductGateway productGateway, ProductImageGateway productImageGateway) {

    public void execute(final Long id) {
        final var product = productGateway.findById(id);
        productImageGateway.deleteAll(product.getProductImages().stream().map(ProductImage::getId).toList());
        productGateway.deleteById(id);
    }

    public void execute(final List<Long> ids) {
        ids.forEach(this::execute);
    }
}
