package com.example.securednote.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.securednote.services.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Local Imports
import com.example.securednote.dto.UserDto;

@RestController
@RequestMapping(path = "/api/auth/register")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/user")
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto) {
        UserDto savedUser = userService.addNewUser(userDto);

        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @PostMapping(path = "/admin")
    public ResponseEntity<UserDto> addAdmin(@RequestBody UserDto userDto) {
        UserDto savedUser = userService.addNewAdmin(userDto);

        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

}
