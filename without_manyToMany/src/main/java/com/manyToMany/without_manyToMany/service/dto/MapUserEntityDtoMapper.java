package com.manyToMany.without_manyToMany.service.dto;

import com.manyToMany.without_manyToMany.persistence.entity.UserEntity;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface MapUserEntityDtoMapper {
    UserEntity mapDtoToEntity(UserDto userDto);
    UserDto mapEntityToDto(UserEntity userEntity);

    List<UserDto> mapEntityToDtoList(List<UserEntity> userEntityList);

}
