package ru.semyon.ui;

import ru.semyon.domain.roles.Customer;
import ru.semyon.service.CustomerService;

import java.util.Scanner;

public class CustomerUI {
    private final Scanner scanner;
    private final CustomerService customerService;
    private static ProductUI productConsoleUI;

    public CustomerUI(Scanner scanner, CustomerService customerService) {
        this.scanner = scanner;
        this.customerService = customerService;
    }

    public void start() {
        while (true) {
            System.out.println("\n=== Меню клиента ===");
            System.out.println("1. Регистрация");
            System.out.println("2. Авторизация");
            System.out.println("0. Выход");
            System.out.print("Выберите действие: ");

            int choice = readIntInput();

            switch (choice) {
                case 1 -> registerCustomer();
                case 2 -> authorizeCustomer();
                case 0 -> {
                    System.out.println("До свидания!");
                    return;
                }
                default -> System.out.println("Неверный ввод, попробуйте снова");
            }
        }
    }

    public void setProductConsoleUI(ProductUI productUI) {
        productConsoleUI = productUI;
    }

    private void registerCustomer() {
        System.out.println("\n=== Регистрация нового клиента ===");

        System.out.print("Введите имя: ");
        String name = scanner.nextLine();

        System.out.print("Введите фамилию: ");
        String surname = scanner.nextLine();

        System.out.print("Введите логин: ");
        String login = scanner.nextLine();

        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();

        System.out.print("Введите email: ");
        String email = scanner.nextLine();

        System.out.print("Введите телефон: ");
        String phone = scanner.nextLine();

        try {
            Customer customer = customerService.registerCustomer(
                    name, surname, login, password, email, phone);
            System.out.println("Регистрация успешна! ID вашего аккаунта: " + customer.getId());
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка регистрации: " + e.getMessage());
        }
    }

    private void authorizeCustomer() {
        System.out.println("\n=== Авторизация ===");

        System.out.print("Введите email: ");
        String email = scanner.nextLine();

        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();

        try {
            Customer customer = customerService.authorizeCustomer(email, password);
            System.out.printf("Авторизация успешна! Добро пожаловать, %s %s%n",
                    customer.getName(), customer.getSurname());
            showCustomerMenu(customer);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка авторизации: " + e.getMessage());
        }
    }


    private void showCustomerInfo(Customer customer) {
        System.out.println("\n=== Ваши данные ===");
        System.out.println("ID: " + customer.getId());
        System.out.println("Имя: " + customer.getName());
        System.out.println("Фамилия: " + customer.getSurname());
        System.out.println("Логин: " + customer.getLogin());
        System.out.println("Email: " + customer.getEmail());
        System.out.println("Телефон: " + customer.getPhoneNumber());
    }

    private void updateCustomerInfo(Customer customer) {
        System.out.println("\n=== Изменение данных ===");

        System.out.print("Введите новое имя (текущее: " + customer.getName() + "): ");
        String name = scanner.nextLine();

        System.out.print("Введите новую фамилию (текущая: " + customer.getSurname() + "): ");
        String surname = scanner.nextLine();

        System.out.print("Введите новый логин (текущий: " + customer.getLogin() + "): ");
        String login = scanner.nextLine();

        System.out.print("Введите новый email (текущий: " + customer.getEmail() + "): ");
        String email = scanner.nextLine();

        System.out.print("Введите новый телефон (текущий: " + customer.getPhoneNumber() + "): ");
        String phone = scanner.nextLine();

        try {
            Customer updated = customerService.updateCustomer(
                    customer.getId(), name, surname, login, email, phone);
            System.out.println("Данные успешно обновлены!");
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка обновления: " + e.getMessage());
        }
    }

    private void addBonusesToCustomer(Customer customer) {
        System.out.print("\nВведите количество бонусов для начисления: ");
        double amount = readDoubleInput();

        try {
            customerService.addBonuses(customer.getId(), amount);
            System.out.println(amount + " бонусов успешно начислено!");
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
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

    private double readDoubleInput() {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Неверный формат числа. Попробуйте снова: ");
            }
        }
    }

    private void showCustomerMenu(Customer customer) {
        while (true) {
            System.out.println("\n=== Личный кабинет ===");
            System.out.println("1. Просмотр моих данных");
            System.out.println("2. Изменить данные");
            System.out.println("3. Начислить бонусы");
            System.out.println("4. Перейти в каталог товаров");
            System.out.println("0. Выйти из аккаунта");
            System.out.print("Выберите действие: ");

            int choice = readIntInput();

            switch (choice) {
                case 1 -> showCustomerInfo(customer);
                case 2 -> updateCustomerInfo(customer);
                case 3 -> addBonusesToCustomer(customer);
                case 4 -> {
                    productConsoleUI.showMainMenu(); // Переход в меню товаров
                    return;
                }
                case 0 -> {
                    System.out.println("Выход из аккаунта...");
                    return;
                }
                default -> System.out.println("Неверный ввод, попробуйте снова");
            }
        }
    }

}
