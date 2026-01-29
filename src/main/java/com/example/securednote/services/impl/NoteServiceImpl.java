package com.example.securednote.services.impl;

import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.Optional;

// Local Imports
import com.example.securednote.dto.NoteDto;
import com.example.securednote.mappers.Mapper;
import com.example.securednote.entities.NoteEntity;
import com.example.securednote.services.NoteService;
import com.example.securednote.repositories.NoteRepository;

@Service
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;
    private final Mapper<NoteEntity, NoteDto> mapper;

    public NoteServiceImpl(NoteRepository noteRepository, Mapper<NoteEntity, NoteDto> mapper) {
        this.noteRepository = noteRepository;
        this.mapper = mapper;
    }

    @Override
    public NoteDto createNote(NoteDto noteDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        NoteEntity noteEntity = mapper.mapFrom(noteDto);
        noteEntity.setOwnerUsername(authentication.getName());

        NoteEntity savedEntity = noteRepository.save(noteEntity);

        return mapper.mapTo(savedEntity);
    }

    @Override
    public List<NoteDto> getAllNotes() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        List<NoteEntity> savedEntities = noteRepository.findByOwnerUsername(authentication.getName());

        return savedEntities.stream().map(mapper::mapTo).toList();
    }

    @Override
    public Optional<NoteDto> getNoteById(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Optional<NoteEntity> savedEntity = noteRepository.findByIdAndOwnerUsername(id, authentication.getName());

        return savedEntity.map(mapper::mapTo);
    }

    @Override
    public Optional<NoteDto> updateNoteById(Long id, NoteDto noteDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Optional<NoteEntity> savedEntity = noteRepository.findByIdAndOwnerUsername(id, authentication.getName());

        return savedEntity
                .map(note -> {
                    mapper.updateExisting(note, noteDto);
                    note.setId(id);
                    note.setOwnerUsername(authentication.getName());

                    NoteEntity savedNote = noteRepository.save(note);

                    return mapper.mapTo(savedNote);
                });
    }

    @Override
    public void deleteNoteById(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        noteRepository.deleteByIdAndOwnerUsername(id, authentication.getName());
    }
}
