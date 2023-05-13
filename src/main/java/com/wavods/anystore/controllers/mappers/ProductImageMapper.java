package com.wavods.anystore.controllers.mappers;

import com.wavods.anystore.controllers.dtos.responses.ProductImageResponseDTO;
import com.wavods.anystore.domains.ProductImage;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductImageMapper {

    ProductImageResponseDTO toDto(ProductImage productImage);

}
