package ru.semyon.repository.impl;

import ru.semyon.domain.Product;
import ru.semyon.repository.ProductRepository;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryProductsRepository implements ProductRepository {
    private final Map<Integer, Product> products = new HashMap<>();
    private final AtomicInteger idGenerator = new AtomicInteger(1);

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    @Override
    public Optional<Product> findById(int id) {
        return Optional.ofNullable(products.get(id));
    }

    @Override
    public List<Product> findByNameContaining(String keyword) {
        String lowerKeyword = keyword.toLowerCase();
        return products.values().stream()
                .filter(p -> p.getName().toLowerCase().contains(lowerKeyword))
                .toList();
    }

    @Override
    public void save(Product product) {
        if (product.getId() == 0) {
            int newId = idGenerator.getAndIncrement();
            Product newProduct = new Product(
                    newId,
                    product.getName(),
                    product.getDescription(),
                    product.getPrice(),
                    product.getCategory(),
                    product.getManufacturer()
            );
            products.put(newId, newProduct);
        } else {
            // Обновление существующего
            products.put(product.getId(), product);
        }
    }
}