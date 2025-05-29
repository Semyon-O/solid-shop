package ru.semyon.domain.roles;

import static ru.semyon.Constants.*;

public class User {

    private Integer Id;
    private String name;
    private String surname;
    private String lastName;
    private String login;
    private String password;
    private String email;

    public User(String name, String surname, String login, String password, String email) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        if (login == null || login.trim().isEmpty()) {
            throw new IllegalArgumentException("Логин не может быть пустым");
        }
        if (login.length() < MIN_PASSWORD_LENGTH || login.length() > MAX_LOGIN_LENGTH) {
            throw new IllegalArgumentException("Логин должен быть от 3 до 20 символов");
        }
        if (!login.matches(REGEX_LOGIN)) {
            throw new IllegalArgumentException("Логин может содержать только буквы, цифры и _");
        }
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Пароль не может быть пустым");
        }
        if (password.length() < 8) {
            throw new IllegalArgumentException("Пароль должен быть не менее 8 символов");
        }
        if (!password.matches(".*\\d.*") || !password.matches(REGEX_PASSWORD)) {
            throw new IllegalArgumentException("Пароль должен содержать хотя бы одну букву и одну цифру");
        }
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email не может быть пустым");
        }

        // Проверка длины email (обычно до 254 символов)
        if (email.length() > EMAIL_MAX_LENGTH) {
            throw new IllegalArgumentException("Email слишком длинный (макс. 254 символа)");
        }

        // Проверка формата email с помощью регулярного выражения
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        if (!email.matches(emailRegex)) {
            throw new IllegalArgumentException("Некорректный формат email");
        }

        this.email = email;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(int andIncrement) {
        this.Id = andIncrement;
    }
}

