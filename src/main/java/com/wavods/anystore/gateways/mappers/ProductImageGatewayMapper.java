package com.wavods.anystore.gateways.mappers;

import com.wavods.anystore.domains.FileUpload;
import com.wavods.anystore.domains.ProductImage;
import com.wavods.anystore.gateways.entities.ProductEntity;
import com.wavods.anystore.gateways.entities.ProductImageEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductImageGatewayMapper {

    @Mapping(target = "id", ignore = true)
    ProductImageEntity toEntity(FileUpload fileUpload, ProductEntity product);

    ProductImage toDomain(ProductImageEntity productImageEntity);
}
