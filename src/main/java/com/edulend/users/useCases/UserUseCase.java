package com.edulend.users.useCases;

import java.util.List;
import java.util.Optional;

import com.edulend.users.datasource.UserDatasource;
import com.edulend.users.models.User;

public class UserUseCase {

    private final UserDatasource userDatasource;

    public UserUseCase(UserDatasource userDatasource) {
        this.userDatasource = userDatasource;
    }

    public User create(User user) {
        Optional<User> existing = userDatasource.findByEmail(user.getEmail());
        if (existing.isPresent()) {
            throw new RuntimeException("El correo ya está registrado.");
        }
        return userDatasource.save(user);
    }

    public List<User> getAll() {
        return userDatasource.findAll();
    }

    public Optional<User> getById(int id) {
        return userDatasource.findById(id);
    }

    public User update(int id, User userData) {
        User existing = userDatasource.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado."));

        existing.setFirst_name(userData.getFirst_name());
        existing.setLast_name(userData.getLast_name());
        existing.setEmail(userData.getEmail());
        existing.setPhone_number(userData.getPhone_number());
        existing.setUser_role(userData.getUser_role());

        return userDatasource.save(existing);
    }

    public void delete(int id) {
        userDatasource.deleteById(id);
    }
}