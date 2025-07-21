package com.allberfelipe.trabalho_02.service;

import com.allberfelipe.trabalho_02.exception.IncorrectPasswordException;
import com.allberfelipe.trabalho_02.exception.TokenNotValidException;
import com.allberfelipe.trabalho_02.exception.UserNotFoundException;
import com.allberfelipe.trabalho_02.model.User;
import com.allberfelipe.trabalho_02.repository.UserRepository;
import com.allberfelipe.trabalho_02.util.PasswordHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    public User login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if (!PasswordHash.verifyPassword(password, user.getPasswordHash())) {
            throw new IncorrectPasswordException("Password incorrect.");
        }

        return user;
    }

    public User validateToken(String token) throws TokenNotValidException {
        if (!jwtService.validateToken(token))
            throw new TokenNotValidException("Invalid token.");

        Long userId = jwtService.getUserIdFromToken(token);

        return userRepository.findById(userId)
                .orElseThrow(() -> new TokenNotValidException("Logged user not found."));
    }
}
