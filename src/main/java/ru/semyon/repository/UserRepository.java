package ru.semyon.repository;

import ru.semyon.domain.roles.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findById(int id);
    Optional<User> findByEmail(String email);
    void save(User user);
    void update(User user);
    boolean existsByEmail(String email);
}