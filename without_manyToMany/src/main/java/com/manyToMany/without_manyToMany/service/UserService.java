package com.manyToMany.without_manyToMany.service;

//import com.manyToMany.without_manyToMany.persistence.entity.RoleEntity;
import com.manyToMany.without_manyToMany.persistence.entity.UserEntity;
//import com.manyToMany.without_manyToMany.persistence.repository.RoleRepository;
import com.manyToMany.without_manyToMany.persistence.repository.UserRepository;
//import com.manyToMany.without_manyToMany.persistence.repository.UserRoleRepository;
import com.manyToMany.without_manyToMany.service.dto.UserDto;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
//    private final RoleRepository roleRepository;
//    private final UserRoleRepository userRoleRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
//            , RoleRepository roleRepository, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
//        this.roleRepository = roleRepository;
//        this.userRoleRepository = userRoleRepository;
    }

    public UserEntity createUser(UserEntity entity) {

        return userRepository.save(entity);
    }

    @SneakyThrows
    public Optional<UserEntity> getUser(Long id) {
        return userRepository.findById(id);
    }

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

//    @SneakyThrows
//    public UserDto updateUser(Long id, UserDto dto) {
//        UserEntity userEntity = userRepository.findById(id)
//                .orElseThrow(() -> new Exception("User Not Found"));
//        if (dto.getFirstName() != null) {
//            userEntity.setFirstName(dto.getFirstName());
//        }
//        if (dto.getLastName() != null) {
//            userEntity.setLastName(dto.getLastName());
//        }
//        if (dto.getRoleName() != null) {
//            RoleEntity roleEntity = new RoleEntity();
//            roleEntity.setId(roleRepository.findRoleId(dto.getRoleName()));
//            roleEntity.setName(dto.getRoleName());
//            RoleEntity finalRoleEntity = roleEntity;
//            UserEntity finalUserEntity = userEntity;
//            userEntity.getListOfUserRole()
//                    .forEach(userRoleEntity -> {
//                        userRoleEntity.setRole(finalRoleEntity);
//                        userRoleEntity.setUser(finalUserEntity);
//                    });
//            roleEntity = roleRepository.save(roleEntity);
//            userEntity = userRepository.save(userEntity);
//        }
//        userEntity = userRepository.save(userEntity);
//        return UserDto.mapEntityToDto(userEntity);
//    }
//
//    @Transactional
//    public void deleteUser(Long id) {
//        roleRepository.deleteById(userRoleRepository.findRoleId(id));
//        userRepository.deleteById(id);
//    }
}
