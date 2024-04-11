package com.example.ejercicio254.auth;

import com.example.ejercicio254.models.Users.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpgradeRequest {
    String userName;
    Role role;
}