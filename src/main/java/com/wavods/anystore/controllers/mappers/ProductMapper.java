package com.wavods.anystore.controllers.mappers;

import com.wavods.anystore.controllers.dtos.requests.ProductRequestDTO;
import com.wavods.anystore.controllers.dtos.responses.ProductResponseDTO;
import com.wavods.anystore.domains.Product;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", uses = PasswordEncoderMapper.class, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {

    Product toDomain(ProductRequestDTO productRequestDTO);

    ProductResponseDTO toDto(Product product);

}
