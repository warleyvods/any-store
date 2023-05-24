package com.wavods.anystore.usecases;

import com.wavods.anystore.gateways.ProductGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public record DeleteProduct(ProductGateway productGateway) {

    public void execute(final Long id) {
        productGateway.deleteById(id);
    }
}
