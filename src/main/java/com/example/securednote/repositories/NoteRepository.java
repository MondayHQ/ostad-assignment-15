package com.example.securednote.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

// Local Imports
import com.example.securednote.entities.NoteEntity;

import java.util.List;
import java.util.Optional;

public interface NoteRepository extends JpaRepository<NoteEntity, Long> {

    List<NoteEntity> findByOwnerUsername(String ownerUsername);

    Optional<NoteEntity> findByIdAndOwnerUsername(Long id, String ownerUsername);

    void deleteByIdAndOwnerUsername(Long id, String ownerUsername);

}
