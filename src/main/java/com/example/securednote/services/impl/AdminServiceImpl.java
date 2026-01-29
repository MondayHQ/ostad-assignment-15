package com.example.securednote.services.impl;

import com.example.securednote.dto.NoteDto;
import com.example.securednote.mappers.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;

// Local Imports
import com.example.securednote.entities.NoteEntity;
import com.example.securednote.services.AdminServices;
import com.example.securednote.repositories.NoteRepository;

@Service
public class AdminServiceImpl implements AdminServices {

    private final NoteRepository noteRepository;
    private final Mapper<NoteEntity, NoteDto> noteMapper;

    public AdminServiceImpl(NoteRepository noteRepository, Mapper<NoteEntity, NoteDto> noteMapper) {
        this.noteRepository = noteRepository;
        this.noteMapper = noteMapper;
    }

    @Override
    public List<NoteDto> getAllNotes() {
        List<NoteEntity> noteEntities = noteRepository.findAll();

        return noteEntities.stream().map(noteMapper::mapTo).toList();
    }

    @Override
    public void deleteNoteById(Long id) {
        noteRepository.deleteById(id);
    }
}
