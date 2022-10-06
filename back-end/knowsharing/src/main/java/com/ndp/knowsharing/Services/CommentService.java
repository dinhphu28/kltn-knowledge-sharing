package com.ndp.knowsharing.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ndp.knowsharing.Repositories.CommentRepo;

@Service
public class CommentService {
    @Autowired
    private CommentRepo repo;
}
