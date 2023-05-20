package com.wavods.anystore.usecases;

import com.wavods.anystore.domains.ProductImage;
import com.wavods.anystore.gateways.ProductImageGateway;

public record FindImage(ProductImageGateway productImageGateway) {

    public ProductImage execute(final Long imageId) {
        return productImageGateway.findById(imageId);
    }
}
