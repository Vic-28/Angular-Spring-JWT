package com.example.ejercicio254.auth;

import com.example.ejercicio254.JWT.JwtService;
import com.example.ejercicio254.Repository.UserRepository;
import com.example.ejercicio254.models.Users.Role;
import com.example.ejercicio254.models.Users.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtService jwtService;
    private final UserRepository userRepository;

    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findAll().stream()
                .filter(u -> {
                    String username = u.getUsername();
                    return username != null && username.equals(request.getUserName());
                })
                .findFirst()
                .orElse(null);
        if (user != null && user.getPassword().equals(request.getPassword())) {
            String token = jwtService.gettoken(user);
            return AuthResponse.builder().token(token).salida("Inicio de sesion correcto").roles(user.getRoles()).build();
        } else {
            return AuthResponse.builder().salida("Error en el login").build();
        }
    }

    public AuthResponse register(RegisterRequest request) {

        User user = User.builder()
                .userName(request.getUserName())
                .roles(List.of(Role.USER))
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .password(request.getPassword())
                .country(request.getCountry()).build();
         userRepository.save(user);


        String token = jwtService.gettoken(user);
        return AuthResponse.builder().token(token).salida("Usuario registrado correctamente").roles(user.getRoles()).build();
    }

    public AuthResponse upgrade(RegisterRequest request) {
        User user = userRepository.findAll().stream()
                .filter(u -> {
                    String username = u.getUsername();
                    return username != null && username.equals(request.getUserName());
                })
                .findFirst()
                .orElse(null);

        if (user != null && !user.getRoles().contains(Role.ADMIN)) {
            List<Role> roles = new ArrayList<>(user.getRoles());
            roles.add(Role.ADMIN);

            user.setRoles(roles);
            return AuthResponse.builder().salida("El usuario: "+ user.getUsername() + " ahora es administrador").roles(user.getRoles()).build();
        } else {
            return AuthResponse.builder().salida("Error en el login").build();
        }
    }

    public AuthResponse demo(LoginRequest request) {
        User user = userRepository.findAll().stream()
                .filter(u -> {
                    String username = u.getUsername();
                    return username != null && username.equals(request.getUserName());
                })
                .findFirst()
                .orElse(null);

        String token = jwtService.gettoken(user);

        return AuthResponse.builder().salida("Bienvenido " + user.getUsername() + " esto es un ejemplo para que veas tus datos").token(token).roles(user.getRoles()).build();

    }



}
