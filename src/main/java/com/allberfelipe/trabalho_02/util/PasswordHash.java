package com.allberfelipe.trabalho_02.util;

import lombok.Getter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordHash {
    
    @Getter
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    
    public static String hashPassword(String plainTextPassword) {
        if (plainTextPassword == null || plainTextPassword.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        return passwordEncoder.encode(plainTextPassword);
    }
    
    public static boolean verifyPassword(String plainTextPassword, String hashedPassword) {
        if (plainTextPassword == null || hashedPassword == null) {
            return false;
        }
        return passwordEncoder.matches(plainTextPassword, hashedPassword);
    }
}