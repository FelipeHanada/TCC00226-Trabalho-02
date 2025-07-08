package com.allberfelipe.trabalho_02.controller;

import com.allberfelipe.trabalho_02.model.User;
import com.allberfelipe.trabalho_02.service.AuthService;
import com.allberfelipe.trabalho_02.util.TokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("login")
    public TokenResponse login(@RequestBody User user) {
        User loggedUser = authService.login(user);
        if (loggedUser != null) {
            return new TokenResponse(loggedUser.getId());
        } else {
            return new TokenResponse(0);
        }
    }
}
