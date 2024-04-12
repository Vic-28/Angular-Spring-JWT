package com.example.ejercicio254.JWT;

import com.example.ejercicio254.models.Users.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;

@Service
public class JwtService {

    //private static final String SECRET_KEY="12345";
    public String getToken(User user) {
        return getToken(new HashMap<>(),user);
    }

    private String getToken(Map<String, Objects> extraClaims, User user) {

        Map<String, Object> claims = new HashMap<>();
        claims.put("ID", user.getId());
        claims.put("Nombre", user.getFirstName());
        claims.put("Apellido", user.getLastName());
        claims.put("Country", user.getCountry());
        claims.put("Roles", user.getRoles());
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(user.getUsername())
                .addClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*24))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getKey() {
        //Hubo que cambiar el formato de encode
        return Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }
}
