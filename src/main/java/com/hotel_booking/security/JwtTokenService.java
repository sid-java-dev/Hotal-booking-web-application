package com.hotel_booking.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.hotel_booking.entity.PropertyUser;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenService {
    private static final  String USER_NAME="username";
    @Value("${jwt.algorithm.key}")
    private String algorithmKey;
    @Value("${jwt.issuer}")
    private String issuer;
    @Value("${jwt.expiry.Duration}")
    private int expiryDuration;
    private Algorithm algorithm;

    @PostConstruct
    public void getAlgorithm(){
        algorithm=Algorithm.HMAC256(algorithmKey);
    }

    public String generateToken(PropertyUser user){
        return JWT.create()
                .withClaim(USER_NAME,user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis()+expiryDuration))
                .withIssuer(issuer)
                .sign(algorithm);
    }
    public String getUsername(String token) {
        DecodedJWT decodedJWT = JWT.require(algorithm).withIssuer(issuer).build().verify(token);
        return decodedJWT.getClaim(USER_NAME).asString();
    }
}
