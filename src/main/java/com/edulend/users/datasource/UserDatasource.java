package com.edulend.users.datasource;

import com.edulend.users.models.User;
import java.util.List;
import java.util.Optional;

public class UserDatasource {

    private final UserRepository userRepository;

    public UserDatasource(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(int id) {
        return userRepository.findById(id);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void deleteById(int id) {
        userRepository.deleteById(id);
    }
}
