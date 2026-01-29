package com.example.securednote.mappers.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

// Local Imports
import com.example.securednote.dto.UserDto;
import com.example.securednote.mappers.Mapper;
import com.example.securednote.entities.UserEntity;

@Component
public class UserMapperImpl implements Mapper<UserEntity, UserDto> {

    private final ModelMapper mapper;

    public UserMapperImpl(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public UserDto mapTo(UserEntity userEntity) {
        return mapper.map(userEntity, UserDto.class);
    }

    @Override
    public UserEntity mapFrom(UserDto userDto) {
        return mapper.map(userDto, UserEntity.class);
    }

    @Override
    public void updateExisting(UserEntity userEntity, UserDto userDto) {
        mapper.map(userDto, userEntity);
    }
}
