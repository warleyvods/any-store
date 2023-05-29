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

    @Query(value = """ 
            SELECT p FROM product p
            WHERE (:status IS NULL OR p.active = :status)
            AND (:keyword IS NULL OR p.name ilike concat('%', :keyword, '%'))""")
    Page<ProductEntity> findByProductWithFilters(@Param("status") final  Boolean status,
                                                 @Param("keyword") final String keyword,
                                                 final Pageable pageable);

}
