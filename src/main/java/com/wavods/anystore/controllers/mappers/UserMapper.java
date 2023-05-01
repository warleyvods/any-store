package com.wavods.anystore.controllers.mappers;



import com.wavods.anystore.annotations.EncodedMapping;
import com.wavods.anystore.controllers.dtos.requests.SignupUserRequestDTO;
import com.wavods.anystore.controllers.dtos.requests.UserPostRequestDTO;
import com.wavods.anystore.controllers.dtos.requests.UserPutPasswordRequestDTO;
import com.wavods.anystore.controllers.dtos.requests.UserPutRequestDTO;
import com.wavods.anystore.controllers.dtos.responses.UserResponseDTO;
import com.wavods.anystore.domains.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = PasswordEncoderMapper.class, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    @Mapping(source = "password", target = "password", qualifiedBy = EncodedMapping.class)
    User toDomain(UserPostRequestDTO userPostRequestDTO);

    @Mapping(source = "password", target = "password", qualifiedBy = EncodedMapping.class)
    User toDomainUser(SignupUserRequestDTO signupUserRequestDTO);

    UserResponseDTO fromDomain(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User updateUserFromDTO(UserPutRequestDTO userPutRequestDTO, @MappingTarget User user);

    @Mapping(source = "password", target = "password", qualifiedBy = EncodedMapping.class)
    User updatePasswordFromDTO(UserPutPasswordRequestDTO userPutPasswordRequestDTO, @MappingTarget User user);

}
