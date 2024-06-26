package com.example.ejercicio254.User.Service;

import com.example.ejercicio254.Repositories.UserRepository;
import com.example.ejercicio254.User.Request.UserRequest;
import com.example.ejercicio254.User.Response.UserResponse;
import com.example.ejercicio254.models.Users.Role;
import com.example.ejercicio254.models.Users.User;
import com.example.ejercicio254.models.Users.UserDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public List<UserDTO> getAllUsers() {
        try {
            return userRepository.findAll()
                    .stream()
                    .map(this::mapToDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to retrieve users from repository", e);
        }
    }

<<<<<<< HEAD
=======
<<<<<<< Updated upstream
=======
>>>>>>> develop-FrontEnd
    private UserDTO mapToDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
<<<<<<< HEAD
                .firstname(user.getFirstName())
                .lastname(user.getLastName())
                .country(user.getCountry())
                .build();
    }
=======
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .country(user.getCountry())
                .build();
    }
>>>>>>> Stashed changes
>>>>>>> develop-FrontEnd

    public UserDTO getUser(Integer id) {
       User user = userRepository.findById(id).orElse(null);
       if (user != null) {
           return UserDTO.builder()
                   .id(user.getId())
                   .username(user.getUsername())
                   .firstName(user.getFirstName())
                   .lastName(user.getLastName())
                   .country(user.getCountry())
                   .build();
       }
       return null;
   }

    public UserResponse updateUser(UserRequest userRequest) {
        User existingUser = userRepository.findById(userRequest.getId()).orElse(null);

        if (existingUser != null) {

            User updatedUser = User.builder()
                    .id(existingUser.getId())
                    .username(userRequest.getUsername())
                    .password(existingUser.getPassword())
                    .firstName(userRequest.getFirstname())
                    .lastName(userRequest.getLastname())
                    .country(userRequest.getCountry())
                    .roles(List.of(Role.USER))
                    .build();

            userRepository.save(updatedUser);
            return new UserResponse("El usuario se ha actualizado de forma correcta");
        } else {
            return new UserResponse("El usuario no existe");
        }
    }

    public User getUserByName(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            return null;
        }
    }

}
