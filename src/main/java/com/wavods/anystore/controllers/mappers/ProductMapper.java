package com.wavods.anystore.controllers.mappers;

import com.wavods.anystore.controllers.dtos.requests.ProductRequestDTO;
import com.wavods.anystore.controllers.dtos.responses.ProductResponseDTO;
import com.wavods.anystore.domains.Product;
import com.wavods.anystore.gateways.ProductImageGateway;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", uses = ProductImageGateway.class, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {

    @Mapping(source = "images", target = "productImages")
    Product toDomain(final ProductRequestDTO productRequestDTO);

    ProductResponseDTO toDto(final Product product);

}
