package com.example.recipes_helper.config;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

@Component 
public class JwtTokenUtil {
    // Генерируем секретный ключ для подписи JWT токенов (HS256)
    private final SecretKey secret;

    public JwtTokenUtil(@Value("${jwt.secret}") String secretString) { //получаем секретный ключ из application.properties
        byte[] keyBytes = Base64.getDecoder().decode(secretString);
        this.secret = Keys.hmacShaKeyFor(keyBytes);
    }
    
    private final long jwtExpirationMs = 86400000; //24 часа

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .setClaims(claims) // Устанавливаем claims (доп. информация)
                .setSubject(userDetails.getUsername()) 
                .setIssuedAt(new Date()) // дата выпуска токена
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs)) // дата истечения
                .signWith(secret) // подписываем токен секретным ключом
                .compact(); // собираем JWT строку
    }


    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token); 
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token)); //совпадет ли юз и не истек ли токен
    }

    public String extractUsername(String token) { //извлечение username из токена
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Универсальный метод для извлечения любого claim из токена
     */
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token); // Получаем все claims
        return claimsResolver.apply(claims); // Применяем функцию для получения нужного значения
    }

    /**
     * Получаем все claims из токена
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secret) // Указываем ключ для проверки подписи
                .build()
                .parseClaimsJws(token) // Парсим токен
                .getBody(); // Получаем claims
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }


    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
