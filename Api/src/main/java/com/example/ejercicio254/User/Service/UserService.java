package com.example.ejercicio254.User.Service;

import com.example.ejercicio254.Repositories.UserRepository;
import com.example.ejercicio254.models.Users.User;
import com.example.ejercicio254.models.Users.UserDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    List<User> getAllUsers()
    {
        return userRepository.findAll();
    }


    User getUserById(Long id)
    {
        return userRepository.findById(id).get();
    }

    User updateUser(Long id, User userUpdated)
    {
        User usuario = userRepository.findById(id).get();
        if (userUpdated.getUsername().isEmpty())
        {
            usuario.setUsername(userUpdated.getUsername());
            usuario.setFirstName(userUpdated.getFirstName());
            usuario.setLastName(userUpdated.getLastName());
            return userUpdated;
        }
        return null;
    }

}
