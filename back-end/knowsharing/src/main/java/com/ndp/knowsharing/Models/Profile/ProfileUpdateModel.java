package com.ndp.knowsharing.Models.Profile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfileUpdateModel {
    private String firstName;

    private String lastName;

    private String email;

    private String avatar;
}
