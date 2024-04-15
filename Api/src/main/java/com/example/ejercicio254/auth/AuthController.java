package com.example.ejercicio254.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = {"http://localhost:4200"})
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    //solo me otorga buenas respuestas del body, si no declaro los datos de entrada como request body
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request)
    {
      return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request)
    {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/upgrade")
    public ResponseEntity<AuthResponse> upgrade(@RequestBody LoginRequest request)
    {
        return  ResponseEntity.ok(authService.upgrade(request));
    }

}