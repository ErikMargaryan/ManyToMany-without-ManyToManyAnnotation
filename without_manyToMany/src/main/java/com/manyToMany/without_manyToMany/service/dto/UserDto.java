package com.manyToMany.without_manyToMany.service.dto;

import com.manyToMany.without_manyToMany.persistence.entity.RoleEntity;
import com.manyToMany.without_manyToMany.persistence.entity.UserEntity;
import com.manyToMany.without_manyToMany.persistence.entity.UserRoleEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String roleName;

    public static UserEntity mapDtoToEntity(UserDto dto) {
        if (dto == null) {
            return null;
        }
        UserEntity entity = new UserEntity();
        entity.setId(dto.getId());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());

        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setName(dto.getRoleName());
        UserRoleEntity userRoleEntity = new UserRoleEntity(entity.getId(), entity, roleEntity);

        entity.setListOfUserRole(List.of(userRoleEntity));


        return entity;
    }

    public static UserDto mapEntityToDto(UserEntity entity) {
        if (entity == null) {
            return null;
        }
        UserDto dto = new UserDto();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setRoleName(getRoles(entity));
        return dto;
    }


    private static String getRoles(UserEntity entity) {
        if (Objects.isNull(entity.getListOfUserRole())) return "User";

        return entity.getListOfUserRole().stream()
                .map(UserRoleEntity::getRole)
                .map(RoleEntity::getName)
                .collect(Collectors.joining(", "));
    }
}
