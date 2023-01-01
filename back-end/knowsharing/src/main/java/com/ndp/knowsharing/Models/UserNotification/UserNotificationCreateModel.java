package com.ndp.knowsharing.Models.UserNotification;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserNotificationCreateModel {
    private String createdBy;

    private String createdByName;

    private String forUser;

    private String title;

    private String content;
}
