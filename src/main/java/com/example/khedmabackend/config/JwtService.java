package com.example.khedmabackend.config;

import com.example.khedmabackend.model.Role;
import com.example.khedmabackend.model.Utilisateur;
import com.example.khedmabackend.model.UtilisateurGoogle;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    private static final String SECRET_KEY="EIS2vBdXnjtZvZpN6q2+6DnY4i5t8seJzA7LVJZZzcs=+KmmKujUYggGyKH++/skfs6+df+855988++/959=fsdfsG+gdsg=F=";
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String extractEmail(String token){

        return extractClaim(token, claims -> claims.get("email")).toString();
    }
    public <T> T extractClaim(String token, Function<Claims,T> claimsResolver){
        final Claims claims=extractAllClaims(token);

        return claimsResolver.apply(claims);
    }
    public String generateToken(UserDetails userDetails, UtilisateurGoogle utilisateur){
        return generateToken(new HashMap<>(),userDetails,utilisateur);
    }
    public String generateToken(Map<String,Object> extraClaims, UserDetails userDetails,UtilisateurGoogle utilisateur){
        return Jwts.builder().setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .claim("role",utilisateur.getRole())
                .claim("username",utilisateur.getNom()+"_"+utilisateur.getPrenom())
                .claim("valid",((Utilisateur)utilisateur).getValide())
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username=extractUsername(token);
        return (username.equals(userDetails.getUsername())&& !isTokenExpired(token));
    }
    public boolean isTokenValid(String token){
        return (!isTokenExpired(token));
    }
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token,Claims::getExpiration);
    }

    public String extractAddresseMail(String token){return extractClaim(token,Claims::getSubject);}

    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody();
    }
    private Key getSignInKey() {
        byte keyBites[]= Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }
}