package com.example.securednote.services;

import com.example.securednote.dto.UserDto;

public interface UserService {

    UserDto addNewUser(UserDto userDto);

    UserDto addNewAdmin(UserDto userDto);

}
