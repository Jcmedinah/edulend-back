
package com.edulend.users.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edulend.users.datasource.UserDatasource;
import com.edulend.users.datasource.UserRepository;
import com.edulend.users.models.User;
import com.edulend.users.useCases.UserUseCase;


@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserUseCase userUseCase;

    @Autowired
    public UserController(UserRepository userRepository) {
        UserDatasource userDatasource = new UserDatasource(userRepository);
        this.userUseCase = new UserUseCase(userDatasource);
    }

    // ✅ Crear un usuario usando query params
    @PostMapping
    public ResponseEntity<User> create(
            @RequestParam String first_name,
            @RequestParam String last_name,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam(required = false) String phone_number,
            @RequestParam(defaultValue = "USER") String user_role) {

        User user = new User();
        user.setFirst_name(first_name);
        user.setLast_name(last_name);
        user.setEmail(email);
        user.setPassword(password);
        user.setPhone_number(phone_number);
        user.setUser_role(user_role);

        return ResponseEntity.ok(userUseCase.create(user));
    }

    // ✅ Obtener todos los usuarios
    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(userUseCase.getAll());
    }

    // ✅ Obtener un usuario por ID usando query param
    @GetMapping("/by-id")
    public ResponseEntity<User> getById(@RequestParam int user_id) {
        return userUseCase.getById(user_id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Actualizar un usuario usando query params
    @PutMapping
    public ResponseEntity<User> update(
            @RequestParam int user_id,
            @RequestParam(required = false) String first_name,
            @RequestParam(required = false) String last_name,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String password,
            @RequestParam(required = false) String phone_number,
            @RequestParam(required = false) String user_role
    ) {
        User existingUser = userUseCase.getById(user_id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (first_name != null) existingUser.setFirst_name(first_name);
        if (last_name != null) existingUser.setLast_name(last_name);
        if (email != null) existingUser.setEmail(email);
        if (password != null) existingUser.setPassword(password);
        if (phone_number != null) existingUser.setPhone_number(phone_number);
        if (user_role != null) existingUser.setUser_role(user_role);

        User updated = userUseCase.update(user_id, existingUser);
        return ResponseEntity.ok(updated);
    }

    // ✅ Eliminar un usuario usando query param
    @DeleteMapping
    public ResponseEntity<String> delete(@RequestParam int user_id) {
        userUseCase.delete(user_id);
        return ResponseEntity.ok("Usuario eliminado correctamente.");
    }
}