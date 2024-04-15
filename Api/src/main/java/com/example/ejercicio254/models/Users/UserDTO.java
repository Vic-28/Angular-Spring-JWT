package com.example.ejercicio254.models.Users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    Integer id;
    String username;
    String firstName;
    String lastName;
    String country;
}
