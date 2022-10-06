package com.ndp.knowsharing.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ndp.knowsharing.Entities.UserVoteState;

public interface UserVoteStateRepo extends JpaRepository<UserVoteState, String> {
    
}
