package com.edulend.users.useCases;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edulend.users.datasource.UserRepository;
import com.edulend.users.models.User;

@Service
public class LoginUseCase {

    private final UserRepository userRepository;

    @Autowired
    public LoginUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User login(String email, String password) {
        Optional<User> userOpt = userRepository.findByEmail(email);

        if (userOpt.isEmpty()) {
            throw new RuntimeException("El usuario no existe");
        }

        User user = userOpt.get();

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        return user;
    }
}