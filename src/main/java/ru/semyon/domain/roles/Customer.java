package ru.semyon.domain.roles;

public class Customer extends User {
    private String phoneNumber;
    private String deliveryAddress;
    private double bonusBalance;

    public Customer(String name, String surname, String login, String password, String email) {
        super(name, surname, login, password, email);
    }

    public void applyBonus(double amount) {
        if (amount > 0) {
            bonusBalance += amount;
        }
    }

    // Геттеры и сеттеры
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
