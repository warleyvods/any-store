package com.wavods.anystore.usecases;

import com.wavods.anystore.domains.ProductImage;
import com.wavods.anystore.gateways.ImageGateway;
import com.wavods.anystore.gateways.ProductGateway;
import com.wavods.anystore.gateways.ProductImageGateway;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
public record CreateProductImage(ProductImageGateway productImageGateway,
                                 ProductGateway productGateway,
                                 ImageGateway imageGateway) {

    public ProductImage execute(final MultipartFile image, final Long productId) throws IOException {
        final var product = productGateway.findById(productId);
        imageGateway.imageValidation(image);
        return productImageGateway.saveWithProduct(image, product);
    }

    public ProductImage execute(final MultipartFile image) throws IOException {
        imageGateway.imageValidation(image);
        return productImageGateway.save(image);
    }
}
