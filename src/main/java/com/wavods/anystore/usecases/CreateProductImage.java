package com.wavods.anystore.usecases;

import com.wavods.anystore.domains.ProductImage;
import com.wavods.anystore.gateways.ProductImageGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CreateProductImage {

    private final ProductImageGateway productImageGateway;

    public ProductImage execute(final MultipartFile image, final Long productId) throws IOException {
        return productImageGateway.save(image, productId);
    }

}
