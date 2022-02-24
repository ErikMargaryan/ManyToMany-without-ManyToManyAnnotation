package com.manyToMany.without_manyToMany.service;

import com.manyToMany.without_manyToMany.persistence.entity.RoleEntity;
import com.manyToMany.without_manyToMany.persistence.entity.UserEntity;
import com.manyToMany.without_manyToMany.persistence.repository.RoleRepository;
import com.manyToMany.without_manyToMany.persistence.repository.UserRepository;
import com.manyToMany.without_manyToMany.service.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public UserDto createUser(UserDto dto) {

        UserEntity userEntity = UserDto.mapDtoToEntity(dto);
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setName(dto.getRoleName());
        UserEntity finalUserEntity = userEntity;
        RoleEntity finalRoleEntity = roleEntity;
        userEntity.getListOfUserRole()
                .forEach(userRoleEntity -> {
                    userRoleEntity.setUser(finalUserEntity);
                    userRoleEntity.setRole(finalRoleEntity);
                });
        roleEntity = roleRepository.save(roleEntity);
        userEntity = userRepository.save(userEntity);

        return UserDto.mapEntityToDto(userEntity);
    }

    public UserDto getUser(Long id) throws Exception {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new Exception("User not found"));
        return UserDto.mapEntityToDto(userEntity);
    }

}
