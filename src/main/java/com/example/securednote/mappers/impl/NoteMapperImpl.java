package com.example.securednote.mappers.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

// Local Imports
import com.example.securednote.dto.NoteDto;
import com.example.securednote.mappers.Mapper;
import com.example.securednote.entities.NoteEntity;

@Component
public class NoteMapperImpl implements Mapper<NoteEntity, NoteDto> {

    private final ModelMapper mapper;

    public NoteMapperImpl(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public NoteDto mapTo(NoteEntity noteEntity) {
        return mapper.map(noteEntity, NoteDto.class);
    }

    @Override
    public NoteEntity mapFrom(NoteDto noteDto) {
        return mapper.map(noteDto, NoteEntity.class);
    }

    @Override
    public void updateExisting(NoteEntity noteEntity, NoteDto noteDto) {
        mapper.map(noteDto, noteEntity);
    }
}
