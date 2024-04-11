package com.example.ejercicio254.auth;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginRequest {
    String userName;
    String password;
}
