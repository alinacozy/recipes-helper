package com.example.recipes_helper.services;

public interface TokenBlacklistService {
    
    public void blacklistToken(String token, long expiryEpochSeconds);
    public boolean isBlacklisted(String token);

}
