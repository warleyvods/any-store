package com.wavods.anystore.usecases;

import com.wavods.anystore.domains.ProductImage;
import com.wavods.anystore.gateways.ProductImageGateway;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public record FindImage(ProductImageGateway productImageGateway) {

    public ProductImage execute(final Long imageId) {
        return productImageGateway.findById(imageId);
    }
}
