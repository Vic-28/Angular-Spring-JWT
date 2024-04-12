package com.example.ejercicio254.auth;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    String username;
    String password;
    String firstName;
    String lastName;
    String country;

}
