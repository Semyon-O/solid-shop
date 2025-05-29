package ru.semyon;


import ru.semyon.domain.Product;
import ru.semyon.repository.UserRepository;
import ru.semyon.repository.impl.InMemoryProductsRepository;
import ru.semyon.repository.impl.InMemoryUserRepository;
import ru.semyon.service.CustomerService;
import ru.semyon.service.ProductsService;
import ru.semyon.ui.CustomerUI;
import ru.semyon.ui.ProductUI;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UserRepository userRepository = new InMemoryUserRepository();
        InMemoryProductsRepository productsRepository = new InMemoryProductsRepository();

        initializeTestProducts(productsRepository);

        CustomerService customerService = new CustomerService(userRepository);
        ProductsService productsService = new ProductsService(productsRepository);

        Scanner scanner = new Scanner(System.in);
        CustomerUI customerUI = new CustomerUI(scanner, customerService);
        ProductUI productUI = new ProductUI(scanner, productsService, customerUI);

        customerUI.setProductConsoleUI(productUI);

        customerUI.start();

        scanner.close();
    }

    private static void initializeTestProducts(InMemoryProductsRepository repository) {
        List<Product> testProducts = Arrays.asList(

                new Product(1, "iPhone 13", 799.99, 50),
                new Product(2, "Samsung Galaxy S21", 699.99, 35),
                new Product(3, "MacBook Pro", 1299.99, 20),

                new Product(4, "Sony WH-1000XM4",
                        "Беспроводные наушники с шумоподавлением", 349.99,
                        "Наушники", "Sony"),

                new Product(5, "Dell XPS 15",
                        "15-дюймовый ноутбук с 4K дисплеем", 1799.99,
                        "Ноутбуки", "Dell"),

                new Product(6, "Nike Air Max",
                        "Беговые кроссовки премиум-класса", 129.99,
                        "Обувь", "Nike"),

                new Product(7, "Kindle Paperwhite",
                        "Электронная книга с подсветкой", 129.99,
                        "Электронные книги", "Amazon"),

                new Product(8, "PlayStation 5",
                        "Игровая консоль нового поколения", 499.99,
                        "Игровые консоли", "Sony"),

                new Product(9, "Bose QuietComfort 35 II",
                        "Наушники с активным шумоподавлением", 299.99,
                        "Наушники", "Bose"),

                new Product(10, "Logitech MX Master 3",
                        "Беспроводная мышь для профессионалов", 99.99,
                        "Компьютерные аксессуары", "Logitech")
        );

        testProducts.getFirst().setDescription("Смартфон с процессором A15 Bionic");
        testProducts.getFirst().setManufacturer("Apple");
        testProducts.get(0).setDiscount(0.1);
        testProducts.get(0).setRating(4.8);

        testProducts.get(1).setDescription("Флагманский смартфон с Android");
        testProducts.get(1).setManufacturer("Samsung");
        testProducts.get(1).setRating(4.6);

        testProducts.get(2).setDescription("Ноутбук с процессором M1 Pro");
        testProducts.get(2).setManufacturer("Apple");
        testProducts.get(2).setDiscount(0.15); // 15% скидка
        testProducts.get(2).setRating(4.9);


        testProducts.forEach(repository::save);
    }
}