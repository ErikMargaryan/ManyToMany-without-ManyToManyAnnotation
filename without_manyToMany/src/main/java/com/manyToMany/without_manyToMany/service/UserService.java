package com.manyToMany.without_manyToMany.service;

import com.manyToMany.without_manyToMany.persistence.entity.RoleEntity;
import com.manyToMany.without_manyToMany.persistence.entity.UserEntity;
import com.manyToMany.without_manyToMany.persistence.repository.RoleRepository;
import com.manyToMany.without_manyToMany.persistence.repository.UserRepository;
import com.manyToMany.without_manyToMany.persistence.repository.UserRoleRepository;
import com.manyToMany.without_manyToMany.service.dto.UserDto;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userRoleRepository = userRoleRepository;
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

    @SneakyThrows
    public UserDto updateUser(Long id, UserDto dto) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new Exception("User Not Found"));
        if (dto.getFirstName() != null) {
            userEntity.setFirstName(dto.getFirstName());
        }
        if (dto.getLastName() != null) {
            userEntity.setLastName(dto.getLastName());
        }
        if (dto.getRoleName() != null) {
            RoleEntity roleEntity = new RoleEntity();
            roleEntity.setName(dto.getRoleName());
            RoleEntity finalRoleEntity = roleEntity;
            UserEntity finalUserEntity = userEntity;
            userEntity.getListOfUserRole().stream()
                    .forEach(userRoleEntity -> {
                        userRoleEntity.setRole(finalRoleEntity);
                        userRoleEntity.setUser(finalUserEntity);
                    });
            roleEntity = roleRepository.save(roleEntity);
            userEntity = userRepository.save(userEntity);
        }
        return UserDto.mapEntityToDto(userEntity);
    }

    @Transactional
    public void deleteUser(Long id) {
        roleRepository.deleteById(userRoleRepository.findRoleId(id));
        userRepository.deleteById(id);
    }
}
