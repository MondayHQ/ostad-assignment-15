package com.example.securednote.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// Local Imports
import com.example.securednote.dto.NoteDto;
import com.example.securednote.services.NoteService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/notes")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping
    public ResponseEntity<NoteDto> addNote(@RequestBody NoteDto noteDto) {
        NoteDto savedNoteDto = noteService.createNote(noteDto);

        return new ResponseEntity<>(savedNoteDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<NoteDto>> getAllNotes() {
        List<NoteDto> allNotes = noteService.getAllNotes();

        return new ResponseEntity<>(allNotes, HttpStatus.OK);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<NoteDto> getNoteById(@PathVariable Long id) {
        Optional<NoteDto> savedNoteDto = noteService.getNoteById(id);

        return savedNoteDto
                .map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<NoteDto> updateNote(@PathVariable Long id, @RequestBody NoteDto noteDto) {
        Optional<NoteDto> savedNoteDto = noteService.updateNoteById(id, noteDto);

        return savedNoteDto
                .map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Object> removeNote(@PathVariable Long id) {
        noteService.deleteNoteById(id);

        return ResponseEntity.noContent().build();
    }

}
