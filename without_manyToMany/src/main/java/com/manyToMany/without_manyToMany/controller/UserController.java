package com.manyToMany.without_manyToMany.controller;

import com.manyToMany.without_manyToMany.persistence.entity.UserEntity;
import com.manyToMany.without_manyToMany.service.UserService;
import com.manyToMany.without_manyToMany.service.dto.MapUserEntityDtoMapper;
import com.manyToMany.without_manyToMany.service.dto.UserDto;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;
    private final MapUserEntityDtoMapper userEntityDtoMapper;

    @Autowired
    public UserController(UserService userService, MapUserEntityDtoMapper userEntityDtoMapper) {
        this.userService = userService;
        this.userEntityDtoMapper = userEntityDtoMapper;
    }


    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable("id") Long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("User ID can't be 0 or little");
        }
        Optional<UserEntity> entity = userService.getUser(id);
        return userEntityDtoMapper
                .mapEntityToDto(entity.get());
    }


    @GetMapping
    public List<UserDto> getUsers() {
        List<UserEntity> userEntityList = userService.getAllUsers();
        return userEntityDtoMapper.mapEntityToDtoList(userEntityList);
    }

    @PostMapping("/registration")
    @SneakyThrows
    public ResponseEntity<UserDto> addUser(@RequestBody UserEntity entity) {
        if (entity.getFirstName() == null) {
            throw new Exception("FirstName is required");
        }
        if (entity.getLastName() == null) {
            throw new Exception("LastName is required");
        }
        return ResponseEntity.ok(userEntityDtoMapper.mapEntityToDto(userService.createUser(entity)));
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long id, @RequestBody UserDto dto) {
//        UserDto user = userService.updateUser(id, dto);
//        if (user == null) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(user);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteUser(@PathVariable("id") Long id) {
//        userService.deleteUser(id);
//    }

}
