package com.example.ejercicio254.JWT;

<<<<<<< HEAD

=======
<<<<<<< Updated upstream
=======

import com.example.ejercicio254.User.Service.UserService;
>>>>>>> Stashed changes
import com.example.ejercicio254.models.Users.User;
>>>>>>> develop-FrontEnd
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.io.Decoders;

@Service
public class JwtService {

<<<<<<< HEAD
    private static final String SECRET_KEY="586E3272357538782F413F4428472B4B6250655368566B597033733676397924";

    public String getToken(UserDetails user) {
        return getToken(new HashMap<>(), user);
    }

    private String getToken(Map<String,Object> extraClaims, UserDetails user) {
=======
<<<<<<< Updated upstream
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
=======
    private static final String SECRET_KEY="586E3272357538782F413F4428472B4B6250655368566B597033733676397924";
    private final UserService userService;

    public JwtService(UserService userService) {
        this.userService = userService;
    }

    public String getToken(UserDetails user) {
        return getToken(new HashMap<>(), user);
    }

    private String getToken(Map<String,Object> extraClaims, UserDetails user) {
        HashMap<String, Object> claims = new HashMap<>();
        User userData = getDatosUser(user.getUsername());
        claims.put("Id", userData.getId());
        claims.put("UserName", userData.getUsername());
        claims.put("Pais",userData.getCountry());
        claims.put("Nombre",userData.getFirstName());
        claims.put("Apellido",userData.getLastName());
        claims.put("Roles",userData.getRoles());


>>>>>>> Stashed changes
>>>>>>> develop-FrontEnd
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .addClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*24))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getKey() {
        byte[] keyBytes=Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
<<<<<<< HEAD
=======
<<<<<<< Updated upstream
}
=======
>>>>>>> develop-FrontEnd

    public String getUsernameFromToken(String token) {
        return getClaim(token, Claims::getSubject);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username=getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername())&& !isTokenExpired(token));
    }

    private Claims getAllClaims(String token)
    {
        return Jwts
                .parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public <T> T getClaim(String token, Function<Claims,T> claimsResolver)
    {
        final Claims claims=getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Date getExpiration(String token)
    {
        return getClaim(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token)
    {
        return getExpiration(token).before(new Date());
    }

<<<<<<< HEAD
}
=======
    private User getDatosUser(String username)
    {
        return userService.getUserByName(username);
    }

}
>>>>>>> Stashed changes
>>>>>>> develop-FrontEnd
