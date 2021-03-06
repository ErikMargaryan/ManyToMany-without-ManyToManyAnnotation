package com.manyToMany.without_manyToMany.controller;

import com.manyToMany.without_manyToMany.service.UserService;
import com.manyToMany.without_manyToMany.service.dto.UserDto;
import lombok.SneakyThrows;
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
    public UserDto getUser(@PathVariable("id") Long id) {
        return userService.getUser(id);
    }

    @PostMapping("/registration")
    @SneakyThrows
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto dto) {
        if (dto.getFirstName() == null) {
            throw new Exception("FirstName is required");
        }
        if (dto.getLastName() == null) {
            throw new Exception("LastName is required");
        }
        dto = userService.createUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long id, @RequestBody UserDto dto) {
        UserDto user = userService.updateUser(id, dto);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
    }

}
