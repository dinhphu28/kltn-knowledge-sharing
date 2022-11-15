package com.ndp.knowsharing.Models.Auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ForgetPasswordUpdateModel {
    private String token;

    private String password;
}