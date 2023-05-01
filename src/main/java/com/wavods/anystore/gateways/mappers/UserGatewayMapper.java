package com.wavods.anystore.gateways.mappers;


import com.wavods.anystore.domains.User;
import com.wavods.anystore.gateways.entities.UserEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserGatewayMapper {

    UserEntity toEntity(User user);

    User toDomain(UserEntity userEntity);

}
