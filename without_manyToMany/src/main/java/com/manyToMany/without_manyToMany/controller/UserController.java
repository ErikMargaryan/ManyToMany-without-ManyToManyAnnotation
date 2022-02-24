package com.manyToMany.without_manyToMany.controller;

import com.manyToMany.without_manyToMany.service.UserService;
import com.manyToMany.without_manyToMany.persistence.entity.UserEntity;
import com.manyToMany.without_manyToMany.service.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable("id") Long id) throws Exception {
        return userService.getUser(id);
    }

    @PostMapping("/registration")
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto dto) throws Exception {
        if (dto.getFirstName() == null) {
            throw new Exception("FirstName is required");
        }
        if (dto.getLastName() == null) {
            throw new Exception("LastName is required");
        }
        dto = userService.createUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

}
