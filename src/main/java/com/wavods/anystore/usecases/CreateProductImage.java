package com.wavods.anystore.usecases;

import com.wavods.anystore.domains.ProductImage;
import com.wavods.anystore.gateways.ImageGateway;
import com.wavods.anystore.gateways.ProductGateway;
import com.wavods.anystore.gateways.ProductImageGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Component
public record CreateProductImage(ProductImageGateway productImageGateway,
                                 ProductGateway productGateway,
                                 ImageGateway imageGateway) {

    public ProductImage execute(final MultipartFile image) throws IOException {
        imageGateway.imageValidation(image);
        return productImageGateway.save(image);
    }
}
