package com.example.ejercicio254.Repository;

import com.example.ejercicio254.models.Users.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
