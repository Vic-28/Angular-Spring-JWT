package com.example.ejercicio254.User.Controller;

import com.example.ejercicio254.User.Request.UserRequest;
import com.example.ejercicio254.User.Response.UserResponse;
import com.example.ejercicio254.User.Service.UserService;
import com.example.ejercicio254.models.Users.UserDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(("/api/v1/user"))
@AllArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200"})
public class UserController {

    private final UserService userService;

    @GetMapping("/findAll")
    public ResponseEntity<List<UserDTO>> findAll()
    {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PutMapping("/update")
    public ResponseEntity<UserResponse> update(@RequestBody UserRequest userRequest)
    {
        return ResponseEntity.ok(userService.updateUser(userRequest));
    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Integer id)
    {
        return ResponseEntity.ok(userService.getUser(id));
    }
}
