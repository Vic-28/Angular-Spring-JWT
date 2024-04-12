package com.example.ejercicio254.User.Service;

import com.example.ejercicio254.Repositories.UserRepository;
import com.example.ejercicio254.User.Request.UserRequest;
import com.example.ejercicio254.User.Response.UserResponse;
import com.example.ejercicio254.models.Users.Role;
import com.example.ejercicio254.models.Users.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAllUsers()
    {
        return userRepository.findAll();
    }


   public User getUser(Long id)
    {
        return userRepository.findById(id).get();
    }

   public UserResponse updateUser(UserRequest userRequest)
    {
        User user = User.builder()
                .id(userRequest.getId())
                .firstName(userRequest.getFirstname())
                .lastName(userRequest.getLastname())
                .country(userRequest.getCountry())
                .roles(List.of(Role.USER))
                .build();

        userRepository.save(user);
        return new UserResponse("El usuario se ha actualizado de forma correcta");
    }

}
