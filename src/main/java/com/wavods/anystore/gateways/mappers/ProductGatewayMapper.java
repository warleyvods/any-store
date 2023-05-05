package com.wavods.anystore.gateways.mappers;

import com.wavods.anystore.domains.Product;
import com.wavods.anystore.gateways.entities.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductGatewayMapper {

    ProductEntity toEntity(Product product);

    Product toDomain(ProductEntity productEntity);

}
