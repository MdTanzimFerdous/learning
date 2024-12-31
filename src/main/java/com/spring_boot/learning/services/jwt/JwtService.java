package com.spring_boot.learning.services.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtService {
    private static final String SECRET_KEY = "b4d4b7042c8054732358d1c738d5e95c3ac1c823993eee6281c24e441c3ed62d";
    public String generateToken(String subject){
        return Jwts.builder()
                .claims()
                .subject(subject)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + (1000*60)))
                .and()
                .signWith(getSignedKey())
                .compact();
    }

    SecretKey getSignedKey(){
        byte[] bytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(bytes);
    }

    public String extractUserName(String token) {
        return extractClaims(token, Claims::getSubject);
    }
    public Date extractExpirationTime(String token){
        return extractClaims(token, Claims::getExpiration);
    }



    private <T> T extractClaims(String token, Function<Claims, T> claimExtractor){

        Claims claims = extractAllClaims(token);

        return claimExtractor.apply(claims);

    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(getSignedKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public Boolean isTokenExpired(String token){
        return extractExpirationTime(token).before(new Date());
    }


    public boolean isValidToken(String token, UserDetails userDetails) {
        String userName = extractUserName(token);
        return userName.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }
}
