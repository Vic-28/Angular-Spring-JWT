package com.example.ejercicio254.auth;

import com.example.ejercicio254.models.Users.Role;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class UpgradeRequest {
    @NonNull
    String username;

    Role role;
}