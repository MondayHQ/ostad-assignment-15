package com.example.securednote.services.impl;

import com.example.securednote.mappers.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

// Local Imports
import com.example.securednote.dto.UserDto;
import com.example.securednote.entities.UserEntity;
import com.example.securednote.services.UserService;
import com.example.securednote.repositories.AuthRepository;

import java.util.HashSet;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final AuthRepository authRepository;
    private final Mapper<UserEntity, UserDto> userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(AuthRepository authRepository, Mapper<UserEntity, UserDto> userMapper, PasswordEncoder passwordEncoder) {
        this.authRepository = authRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto addNewUser(UserDto userDto) {
        UserEntity userEntity = userMapper.mapFrom(userDto);
        userEntity.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userEntity.setRoles(new HashSet<>(List.of("USER")));

        UserEntity savedUserEntity = authRepository.save(userEntity);

        return userMapper.mapTo(savedUserEntity);
    }

    @Override
    public UserDto addNewAdmin(UserDto userDto) {
        UserEntity userEntity = userMapper.mapFrom(userDto);
        userEntity.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userEntity.setRoles(new HashSet<>(List.of("ADMIN")));

        UserEntity savedUserEntity = authRepository.save(userEntity);

        return userMapper.mapTo(savedUserEntity);
    }
}
