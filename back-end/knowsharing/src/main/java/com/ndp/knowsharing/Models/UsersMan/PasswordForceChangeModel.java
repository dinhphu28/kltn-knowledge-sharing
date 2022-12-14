package com.ndp.knowsharing.Models.UsersMan;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PasswordForceChangeModel {
    private String username;

    private String newPassword;
}
