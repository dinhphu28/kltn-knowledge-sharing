package com.ndp.knowsharing.Utils.Auth.JWT;

import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

public class jwtSecurity implements myJWT{

    private String secret = "motconmeo";

    @Override
    public String GenerateToken(String username) {
        String token = "";
        
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            // token = "meo";
            token = JWT.create()
                .withIssuer("auth0")
                .withSubject(username)
                .sign(algorithm);
        } catch (Exception e) {
            //Invalid Signing configuration / Couldn't convert Claims.
        }

        return token;
    }

    @Override
    public Boolean VerifyToken(String token, String username) {
        Boolean kk = false;

        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("auth0")
                .withSubject(username)
                .build();

            DecodedJWT decodedJWT = verifier.verify(token);

            kk = true;
        } catch (Exception e) {
            //TODO: handle exception
        }

        return kk;
    }

    @Override
    public String GenerateEmailVerificationToken(String username, String email) {
        String token = "";

        long miliSeconds = System.currentTimeMillis() + 300000;
        
        Date expDate = new Date(miliSeconds);
        
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            // token = "meo";
            token = JWT.create()
                .withIssuer("auth0")
                .withSubject(username)
                .withExpiresAt(expDate)
                .withClaim("userEmail", email)
                .sign(algorithm);
        } catch (Exception e) {
            //Invalid Signing configuration / Couldn't convert Claims.
        }

        return token;
    }

    public Boolean VerifyEmailVerificationToken(String token, String username, String email) {
        Boolean kk = false;

        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("auth0")
                .withSubject(username)
                .withClaim("userEmail", email)
                .build();

            DecodedJWT decodedJWT = verifier.verify(token);

            kk = true;
        } catch (Exception e) {
            //TODO: handle exception
        }

        return kk;
    }

    public String GenerateForgetPasswordToken(String username) {
        String token = "";

        long miliSeconds = System.currentTimeMillis() + 300000;
        
        Date expDate = new Date(miliSeconds);
        
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            // token = "meo";
            token = JWT.create()
                .withIssuer("auth0")
                .withSubject(username)
                .withExpiresAt(expDate)
                .sign(algorithm);
        } catch (Exception e) {
            //Invalid Signing configuration / Couldn't convert Claims.
        }

        return token;
    }
}