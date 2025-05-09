package com.example.recipes_helper.services.Impl;

import org.springframework.stereotype.Service;

import com.example.recipes_helper.services.TokenBlacklistService;

import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TokenBlacklistServiceImpl implements TokenBlacklistService {

    // Храним токен и время его истечения (epoch seconds)
    private final Map<String, Long> blacklist = new ConcurrentHashMap<>();

    /**
    * Добавить токен в blacklist
    */
    @Override
    public void blacklistToken(String token, long expiryEpochSeconds) {
        blacklist.put(token, expiryEpochSeconds);
    }

    /**
    * Проверить, находится ли токен в blacklist и не истёк ли он
    */
    @Override
    public boolean isBlacklisted(String token) {
        Long expiry = blacklist.get(token); // получаем время истечения токена
        if (expiry == null) { //если токен не найден в блеклисте
            return false;
        }
        if (expiry < Instant.now().getEpochSecond()) {
            // Токен истёк - удаляем из блеклиста
            blacklist.remove(token);
            return false;
        }
        return true;
    }
}
