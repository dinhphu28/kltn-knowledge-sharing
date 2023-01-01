package com.ndp.knowsharing.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ndp.knowsharing.Entities.UserNotification;

@Repository
public interface UserNotificationRepo extends JpaRepository<UserNotification, String> {
    List<UserNotification> findByForUserAndHidden(String username, Integer hidden);
}
