package ru.semyon.repository;

import ru.semyon.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<Product> findAll();
    Optional<Product> findById(int id);
    List<Product> findByNameContaining(String keyword);
    void save(Product product);
}