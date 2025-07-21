package com.allberfelipe.trabalho_02.controller;

import com.allberfelipe.trabalho_02.dto.LoginRequest;
import com.allberfelipe.trabalho_02.exception.TokenNotValidException;
import com.allberfelipe.trabalho_02.model.User;
import com.allberfelipe.trabalho_02.service.AuthService;
import com.allberfelipe.trabalho_02.service.JwtService;
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

    @Autowired
    private JwtService jwtService;    

    @PostMapping("login")
    public TokenResponse login(@RequestBody LoginRequest credentials) {
        User loggedUser = authService.login(credentials.getEmail(), credentials.getPassword());

        String jwtToken = jwtService.generateToken(loggedUser.getId());
        return new TokenResponse(jwtToken);
    }

    @GetMapping("user")
    public User user(@RequestParam("token") String token) {
        Optional<User> loggedUser = authService.getUserByToken(token);

        if (loggedUser.isEmpty())
            throw new TokenNotValidException("Logged user not found.");

        return loggedUser.get();
    }
}
