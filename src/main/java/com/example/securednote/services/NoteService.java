package com.example.securednote.services;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Local Imports
import com.example.securednote.dto.NoteDto;

@Service
public interface NoteService {

    NoteDto createNote(NoteDto noteDto);

    List<NoteDto> getAllNotes();

    Optional<NoteDto> getNoteById(Long id);

    Optional<NoteDto> updateNoteById(Long id, NoteDto noteDto);

    void deleteNoteById(Long id);

}
