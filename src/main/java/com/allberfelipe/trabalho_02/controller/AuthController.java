package com.allberfelipe.trabalho_02.controller;

import com.allberfelipe.trabalho_02.exception.TokenNotValidException;
import com.allberfelipe.trabalho_02.model.User;
import com.allberfelipe.trabalho_02.service.AuthService;
import com.allberfelipe.trabalho_02.util.TokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("login")
    public TokenResponse login(@RequestBody User user) {
        Optional<User> loggedUser = authService.login(user);

        return loggedUser.map(value -> new TokenResponse(value.getId())).orElseGet(() -> new TokenResponse(0));
    }

    @GetMapping("user/{token}")
    public User user(@PathVariable("token") String token) {
        Optional<User> loggedUser = authService.getUserByToken(token);

        if (loggedUser.isEmpty())
            throw new TokenNotValidException("Logged user not found.");

        return loggedUser.get();
    }
}
