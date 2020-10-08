package com.lifebank.component.JWT;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

@Component
public class JWTManager implements IJWTManager {

    public String createJWT(String id, String issuer, String subject, long ttlMillis) {

        //The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //We will sign our JWT with our ApiKey secret
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("-hBro3-jH5Cus3zYnpn0jQdBFTaZlXJlUFRkhg4rLqITeYeL1LkLKqxVY8OggJpa9dz-WUEi20vHqW5Da9Af_tz33pF-9bNeGhNhkm3PqP0yE-DFuizY2lybC1kdn6wMiSFKXRXejA576udDIe0M1vPWsZQcHbHJNzN6p6EAkbd8w2UtF6CulfSBLlg7dGniI7GR2wBNFPV-6mi0PSPvtrMY-h9bgLonl_m2GVQNBMxiI4mR9DkU442Sazy6RYrraXeeT8K5HigHiT5jCrQBTy5vHAGOaZrXkMPw0zAOqw8Sj1-oBM94SUTOHk9D2WFuMBy2AnmjSYb06R56wpP3jQ");
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder().setId(id)
                .setIssuedAt(now)
                .setSubject(subject)
                .setIssuer(issuer)
                .signWith(signingKey);

        //if it has been specified, let's add the expiration
        if (ttlMillis > 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        //Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }

    public Claims decodeJWT(String jwt) {
        //This line will throw an exception if it is not a signed JWS (as expected)
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary("-hBro3-jH5Cus3zYnpn0jQdBFTaZlXJlUFRkhg4rLqITeYeL1LkLKqxVY8OggJpa9dz-WUEi20vHqW5Da9Af_tz33pF-9bNeGhNhkm3PqP0yE-DFuizY2lybC1kdn6wMiSFKXRXejA576udDIe0M1vPWsZQcHbHJNzN6p6EAkbd8w2UtF6CulfSBLlg7dGniI7GR2wBNFPV-6mi0PSPvtrMY-h9bgLonl_m2GVQNBMxiI4mR9DkU442Sazy6RYrraXeeT8K5HigHiT5jCrQBTy5vHAGOaZrXkMPw0zAOqw8Sj1-oBM94SUTOHk9D2WFuMBy2AnmjSYb06R56wpP3jQ"))
                .parseClaimsJws(jwt).getBody();

        return claims;
    }

}
