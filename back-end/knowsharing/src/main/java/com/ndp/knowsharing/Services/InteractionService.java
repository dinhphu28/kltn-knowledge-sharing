package com.ndp.knowsharing.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ndp.knowsharing.Repositories.InteractionRepo;

@Service
public class InteractionService {
    @Autowired
    private InteractionRepo repo;

    
}
