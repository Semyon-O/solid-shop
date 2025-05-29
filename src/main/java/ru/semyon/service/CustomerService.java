package ru.semyon.service;

import ru.semyon.domain.roles.Customer;
import ru.semyon.repository.UserRepository;


public class CustomerService {
    private final UserRepository userRepository;

    public CustomerService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Customer registerCustomer(String name, String surname, String login,
                                     String password, String email,
                                     String phoneNumber) {
        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Пользователь с таким email уже существует");
        }

        Customer customer = new Customer(name, surname, login, password, email);
        customer.setPhoneNumber(phoneNumber);

        userRepository.save(customer);
        return customer;
    }

    public Customer authorizeCustomer(String email, String password) throws IllegalArgumentException {
        // Валидация входных параметров
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email не может быть пустым");
        }

        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("Пароль не может быть пустым");
        }

        return userRepository.findByEmail(email)
                .filter(user -> user instanceof Customer)
                .map(user -> (Customer) user)
                .filter(customer -> checkPassword(customer, password))
                .orElseThrow(() -> new IllegalArgumentException("Неверный email или пароль"));
    }

    private boolean checkPassword(Customer customer, String inputPassword) {
        return customer.getPassword().equals(inputPassword);
    }

    public Customer updateCustomer(int customerId, String name, String surname,
                                   String login, String email, String phoneNumber) {
        Customer customer = (Customer) userRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Клиент не найден"));

        customer.setName(name);
        customer.setSurname(surname);
        customer.setLogin(login);
        customer.setEmail(email);
        customer.setPhoneNumber(phoneNumber);

        userRepository.update(customer);
        return customer;
    }

    public void addBonuses(int customerId, double amount) {
        Customer customer = (Customer) userRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Клиент не найден"));
        customer.applyBonus(amount);
        userRepository.update(customer);
    }

}