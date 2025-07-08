package com.allberfelipe.trabalho_02.service;

import com.allberfelipe.trabalho_02.model.Article;
import com.allberfelipe.trabalho_02.model.User;
import com.allberfelipe.trabalho_02.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    public User login(User user) {
        return userRepository.findByEmailAndPasswordHash(user.getEmail(), user.getPasswordHash());
    }
}
