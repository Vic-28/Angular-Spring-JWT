package com.example.ejercicio254.User.Controller;

import com.example.ejercicio254.User.Request.UserRequest;
import com.example.ejercicio254.User.Response.UserResponse;
import com.example.ejercicio254.User.Service.UserService;
import com.example.ejercicio254.models.Users.User;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/api/v1/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/findAll")
    public ResponseEntity<List<User>> findAll()
    {
        List<User> usuarios = userService.getAllUsers();
        return ResponseEntity.ok(usuarios);
    }

    @PutMapping("/update")
    public ResponseEntity<UserResponse> update(@RequestBody UserRequest userRequest)
    {
        return ResponseEntity.ok(userService.updateUser(userRequest));
    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id)
    {
        User user = userService.getUser(id);
        if (user==null)
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }
}
