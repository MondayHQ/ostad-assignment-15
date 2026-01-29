package com.example.securednote.services;

import com.example.securednote.dto.NoteDto;

import java.util.List;

public interface AdminServices {

    List<NoteDto> getAllNotes();

    void deleteNoteById(Long id);

}
