package com.ndp.knowsharing.Models.UsersMan;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsersManModelReturn {
    private String username;

    private Boolean active;

    private String avatar;
}
