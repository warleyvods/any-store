package com.wavods.anystore.usecases;

import com.wavods.anystore.domains.Product;
import com.wavods.anystore.gateways.ProductGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindProduct {

    private final ProductGateway productGateway;

    public Page<Product> execute(final Pageable pageable) {
        return productGateway.getAll(pageable);
    }
}
