package com.ndp.knowsharing.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ndp.knowsharing.Repositories.CommentReportRepo;

@Service
public class CommentReportService {
    @Autowired
    private CommentReportRepo repo;
}
