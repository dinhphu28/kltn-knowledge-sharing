package com.ndp.knowsharing.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ndp.knowsharing.Entities.Interaction;

public interface InteractionRepo extends JpaRepository<Interaction, String> {
    
}
