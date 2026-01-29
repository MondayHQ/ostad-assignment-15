package com.example.securednote.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// Local Imports
import com.example.securednote.entities.UserEntity;

public interface AuthRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);

}
