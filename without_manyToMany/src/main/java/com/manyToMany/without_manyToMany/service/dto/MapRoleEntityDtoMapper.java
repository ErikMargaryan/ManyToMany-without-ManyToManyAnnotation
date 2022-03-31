package com.manyToMany.without_manyToMany.service.dto;

import com.manyToMany.without_manyToMany.persistence.entity.RoleEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapRoleEntityDtoMapper {
    RoleDto mapEntityToDto(RoleEntity roleEntity);
    RoleEntity mapRoleDtoToEntity(RoleDto roleDto);
}
