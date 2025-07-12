package com.allberfelipe.trabalho_02.service;

import com.allberfelipe.trabalho_02.model.User;
import com.allberfelipe.trabalho_02.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> login(User user) {
        return userRepository.findByEmailAndPasswordHash(user.getEmail(), user.getPasswordHash());
    }

    public Optional<User> getUserByToken(String token) {
        long id = Long.parseLong(token);
        return userRepository.findById(id);
    }
}
