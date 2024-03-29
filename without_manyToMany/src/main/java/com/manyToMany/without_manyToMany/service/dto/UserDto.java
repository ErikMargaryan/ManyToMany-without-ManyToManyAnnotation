package com.manyToMany.without_manyToMany.service.dto;

import com.manyToMany.without_manyToMany.persistence.entity.RoleEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;

    private String firstName;

    private String lastName;

    private Set<RoleEntity> roles;
}
