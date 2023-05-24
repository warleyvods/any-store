package com.wavods.anystore.controllers.mappers;

import com.wavods.anystore.controllers.dtos.requests.ProductPostRequestDTO;
import com.wavods.anystore.controllers.dtos.requests.ProductPutRequestDTO;
import com.wavods.anystore.controllers.dtos.responses.ProductResponseDTO;
import com.wavods.anystore.domains.Product;
import com.wavods.anystore.gateways.ProductImageGateway;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = ProductImageGateway.class, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {

    Product toDomain(final ProductPostRequestDTO productPostRequestDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Product updateProductFromDTO(final ProductPutRequestDTO productPutRequestDTO, @MappingTarget final Product product);

    ProductResponseDTO toDto(final Product product);
}
