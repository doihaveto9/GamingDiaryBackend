package com.example.GamingDiaryBackend.Controllers;

import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.GamingDiaryBackend.Services.AuthenticationService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/user")
public class AuthenticationController {
    
    private final AuthenticationService authService;

    public AuthenticationController(AuthenticationService authService) {
        this.authService = authService;
    }

    @PostMapping("/logInUser")
    public String logInUser(@RequestBody Map<String, String> body){
        String username = body.get("username");
        String password = body.get("password");

        return authService.authenticateUser(username, password);
    }

    @PostMapping("/registerUser")
    public void registerUser(@RequestBody Map<String, String> body){
        String username = body.get("username");
        String password = body.get("password");
        String email = body.get("email");

        authService.registerUser(username, password, email);
    }
}
