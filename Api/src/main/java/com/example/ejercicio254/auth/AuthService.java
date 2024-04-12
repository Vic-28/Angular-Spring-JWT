package com.example.ejercicio254.auth;

import com.example.ejercicio254.JWT.JwtService;
import com.example.ejercicio254.Repositories.UserRepository;
import com.example.ejercicio254.models.Users.Role;
import com.example.ejercicio254.models.Users.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        User user =userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token=jwtService.getToken(user);
        return AuthResponse.builder()
                .token(token)
                .build();

    }

    public AuthResponse register(RegisterRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode( request.getPassword()))
                .firstName(request.getFirstName())
                .lastName(request.lastName)
                .country(request.getCountry())
                .roles(List.of(Role.USER))
                .build();

        userRepository.save(user);

        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();

    }


    public AuthResponse upgrade(LoginRequest request) {
        Optional<User> userOptional = userRepository.findByUsername(request.getUsername());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (!user.getRoles().contains(Role.ADMIN)) {
                List<Role> roles = new ArrayList<>(user.getRoles());
                roles.add(Role.ADMIN);
                user.setRoles(roles);
                userRepository.save(user);
                String token = jwtService.getToken(user);
                return AuthResponse.builder().token(token).build();
            } else {
                return AuthResponse.builder().build();
            }
        } else {
            return AuthResponse.builder().build();
        }
    }


    public AuthResponse demo(LoginRequest request) {
        User user = userRepository.findAll().stream()
                .filter(u -> {
                    String username = u.getUsername();
                    return username != null && username.equals(request.getUsername());
                })
                .findFirst()
                .orElse(null);

        String token = jwtService.getToken(user);

        return AuthResponse.builder().token(token).build();
    }



}
