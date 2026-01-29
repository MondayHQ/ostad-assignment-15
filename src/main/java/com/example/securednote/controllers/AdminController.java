package com.example.securednote.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Local Imports
import com.example.securednote.dto.NoteDto;
import com.example.securednote.services.AdminServices;

@RestController
@RequestMapping(path = "/api/admin")
public class AdminController {

    private final AdminServices adminServices;

    public AdminController(AdminServices adminServices) {
        this.adminServices = adminServices;
    }

    @GetMapping(path = "/notes")
    public ResponseEntity<List<NoteDto>> getAllNotes() {
        List<NoteDto> allNotes = adminServices.getAllNotes();

        return ResponseEntity.ok(allNotes);
    }

    @DeleteMapping(path = "/notes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteNoteById(@PathVariable Long id) {
        adminServices.deleteNoteById(id);
    }

}
