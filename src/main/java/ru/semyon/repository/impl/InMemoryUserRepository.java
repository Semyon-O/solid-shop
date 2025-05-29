package ru.semyon.repository.impl;

import ru.semyon.domain.roles.User;
import ru.semyon.repository.UserRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryUserRepository implements UserRepository {
    private final Map<Integer, User> users = new HashMap<>();
    private final Map<String, User> emailIndex = new HashMap<>();
    private final AtomicInteger idGenerator = new AtomicInteger(1);

    @Override
    public void save(User user) {
        if (user.getId() == null) {
            user.setId(idGenerator.getAndIncrement());
        }

        if (emailIndex.containsKey(user.getEmail())) {
            throw new IllegalArgumentException("Email уже существует");
        }

        users.put(user.getId(), user);
        emailIndex.put(user.getEmail(), user);
    }

    @Override
    public void update(User user) {
        if (!users.containsKey(user.getId())) {
            throw new IllegalArgumentException("Пользователь не найден");
        }

        User existingUser = users.get(user.getId());
        if (!existingUser.getEmail().equals(user.getEmail())) {
            if (emailIndex.containsKey(user.getEmail())) {
                throw new IllegalArgumentException("Новый email уже занят");
            }
            emailIndex.remove(existingUser.getEmail());
            emailIndex.put(user.getEmail(), user);
        }

        users.put(user.getId(), user);
    }

    @Override
    public boolean existsByEmail(String email) {
        return emailIndex.containsKey(email);
    }

    @Override
    public Optional<User> findById(int id) {
        return Optional.ofNullable(users.get(id));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(emailIndex.get(email));
    }


}