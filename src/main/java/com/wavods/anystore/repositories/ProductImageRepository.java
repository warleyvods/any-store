package com.wavods.anystore.repositories;

import com.wavods.anystore.gateways.entities.ProductImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageRepository extends JpaRepository<ProductImageEntity, Long> {
}
