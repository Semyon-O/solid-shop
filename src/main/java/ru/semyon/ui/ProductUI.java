package ru.semyon.ui;

import ru.semyon.domain.Product;
import ru.semyon.service.ProductsService;

import java.util.Scanner;

public class ProductUI {
    private final Scanner scanner;
    private final ProductsService productsService;
    private final CustomerUI customerUI;

    public ProductUI(Scanner scanner, ProductsService productsService, CustomerUI customerUI) {
        this.scanner = scanner;
        this.productsService = productsService;
        this.customerUI = customerUI;
    }

    public void showMainMenu() {
        while (true) {
            System.out.println("\n=== Меню товаров ===");
            System.out.println("1. Просмотр всех товаров");
            System.out.println("2. Поиск товара по ID");
            System.out.println("3. Вернуться в меню пользователя");
            System.out.println("0. Выход из программы");
            System.out.print("Выберите действие: ");

            int choice = readIntInput();

            switch (choice) {
                case 1 -> showAllProducts();
                case 2 -> showProductById();
                case 3 -> {
                    customerUI.start();
                    return;
                }
                case 0 -> {
                    System.out.println("Выход из программы...");
                    System.exit(0);
                }
                default -> System.out.println("Неверный ввод, попробуйте снова");
            }
        }
    }

    private void showAllProducts() {
        System.out.println("\n=== Список всех товаров ===");
        productsService.getAllProducts().forEach(this::printProductDetails);
    }

    private void showProductById() {
        System.out.print("\nВведите ID товара: ");
        int id = readIntInput();

        try {
            Product product = productsService.getConcreteProduct(id);
            printProductDetails(product);
            showProductActions(product);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private void printProductDetails(Product product) {
        System.out.println("\nID: " + product.getId());
        System.out.println("Название: " + product.getName());
        System.out.println("Описание: " + product.getDescription());
        System.out.println("Цена: " + product.getPrice() + " руб.");
        System.out.println("Категория: " + product.getCategory());
        System.out.println("Производитель: " + product.getManufacturer());
    }

    private void showProductActions(Product product) {
        System.out.println("\nДействия с товаром:");
        System.out.println("1. Добавить в корзину");
        System.out.println("2. Вернуться к списку товаров");
        System.out.print("Выберите действие: ");

        int choice = readIntInput();
        if (choice == 1) {
            System.out.println("Товар '" + product.getName() + "' добавлен в корзину");
        }
    }

    private int readIntInput() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Неверный формат числа. Попробуйте снова: ");
            }
        }
    }
}