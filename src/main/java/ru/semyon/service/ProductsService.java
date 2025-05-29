package ru.semyon.service;

import ru.semyon.repository.impl.InMemoryProductsRepository;
import ru.semyon.domain.Product;

import java.util.List;

public class ProductsService {

    private static InMemoryProductsRepository productsRepository;

    public ProductsService(InMemoryProductsRepository productsRepository) {
        ProductsService.productsRepository = productsRepository;
    }

    public List<Product> getAllProducts() {
        return productsRepository.findAll();
    }

    public Product getConcreteProduct(Integer id) {

        if (id == null) {
            throw new IllegalArgumentException("ID продукта не может быть null");
        }

        return productsRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Товар не найден"));

    }
}
