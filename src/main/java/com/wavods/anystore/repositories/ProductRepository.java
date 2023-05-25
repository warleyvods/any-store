package com.wavods.anystore.repositories;


import com.wavods.anystore.gateways.entities.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    @Query("SELECT p FROM product p WHERE 1=1 " +
            " AND (:status IS NULL OR p.active = :status) ")
    Page<ProductEntity> findByProductWithFilters(@Param("status") final  Boolean status, final Pageable pageable);

}
