package com.example.GamingDiaryBackend.Controllers;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.GamingDiaryBackend.Services.AuthenticationService;
import com.google.rpc.context.AttributeContext.Response;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/user")
public class AuthenticationController {
    
    private final AuthenticationService authService;

    public AuthenticationController(AuthenticationService authService) {
        this.authService = authService;
    }

    @PostMapping("/logInUser")
    public ResponseEntity<String> logInUser(@RequestBody Map<String, String> body){
        String username = body.get("username");
        String password = body.get("password");

        // body.forEach((key, value) -> System.out.println(key + ": " + value));

        String result = authService.authenticateUser(username, password);
        
        if(result == null || result.isEmpty())
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed, username and password not found");
        else
            return ResponseEntity.ok(result);
    }

    @PostMapping("/registerUser")
    public ResponseEntity<Void> registerUser(@RequestBody Map<String, String> body){
        String username = body.get("username");
        String password = body.get("password");
        String email = body.get("email");

        boolean success = authService.registerUser(username, password, email);

        if(success)
            return ResponseEntity.status(HttpStatus.CREATED).build();
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
