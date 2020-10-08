package com.lifebank.component.JWT;

import io.jsonwebtoken.Claims;

public interface IJWTManager {
    public String createJWT(String id, String issuer, String subject, long ttlMillis);

    public Claims decodeJWT(String jwt);
}
