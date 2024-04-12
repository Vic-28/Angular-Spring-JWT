package com.example.ejercicio254.auth;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {
    private final AuthService authService;
    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    @PostMapping("/login")
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
    public ResponseEntity<AuthResponse> upgrade(@RequestBody RegisterRequest request)
    {
        return  ResponseEntity.ok(authService.upgrade(request));
    }
    @PostMapping("/demo")
    public ResponseEntity<AuthResponse> demo(@RequestBody LoginRequest request)
    {
        return  ResponseEntity.ok(authService.demo(request));
    }
}