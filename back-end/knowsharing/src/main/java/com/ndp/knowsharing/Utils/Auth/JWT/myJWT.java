package com.ndp.knowsharing.Utils.Auth.JWT;

public interface myJWT {
    String GenerateToken(String username);
    
    Boolean VerifyToken(String token, String username);

    String GenerateEmailVerificationToken(String username, String email);
    
    Boolean VerifyEmailVerificationToken(String token, String username, String email);

    String GenerateForgetPasswordToken(String username);
}
