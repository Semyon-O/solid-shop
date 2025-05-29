package ru.semyon.domain;

import static ru.semyon.Constants.*;

public class Product {

    private int id;
    private String name;
    private String description;
    private double price;
    private int stockQuantity;
    private String category;
    private String manufacturer;
    private String sku;
    private double discount;
    private double rating;
    private boolean isAvailable;

    public Product(int id, String name, double price, int stockQuantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public Product(int newId, String name, String description, double price, String category, String manufacturer) {
        id = newId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.manufacturer = manufacturer;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Название товара не может быть пустым");
        }
        if (name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("Название слишком длинное (макс. " + MAX_NAME_LENGTH + " символов)");
        }
        this.name = name;
    }

    public void setPrice(double price) {
        if (price < MIN_PRICE || price > MAX_PRICE) {
            throw new IllegalArgumentException(String.format(
                    "Цена должна быть в диапазоне %.2f - %.2f", MIN_PRICE, MAX_PRICE));
        }
        this.price = price;
    }

    public void setStockQuantity(int stockQuantity) {
        if (stockQuantity < MIN_STOCK) {
            throw new IllegalArgumentException("Количество на складе не может быть отрицательным");
        }
        this.stockQuantity = stockQuantity;
    }

    public void setDiscount(double discount) {
        if (discount < MIN_DISCOUNT || discount > MAX_DISCOUNT) {
            throw new IllegalArgumentException(String.format(
                    "Скидка должна быть в диапазоне %.0f-%.0f%%", MIN_DISCOUNT, MAX_DISCOUNT));
        }
        this.discount = discount;
    }

    public void setRating(double rating) {
        if (rating < MIN_RATING || rating > MAX_RATING) {
            throw new IllegalArgumentException(String.format(
                    "Рейтинг должен быть в диапазоне %.1f-%.1f", MIN_RATING, MAX_RATING));
        }
        this.rating = rating;
    }

    public void setSku(String sku) {
        if (sku != null && !sku.matches("[A-Z0-9-]+")) {
            throw new IllegalArgumentException("SKU может содержать только буквы A-Z, цифры и дефисы");
        }
        this.sku = sku;
    }

    public void setDescription(String description) {
        if (description != null && description.length() > MAX_DESCRIPTION_LENGTH) {
            throw new IllegalArgumentException("Описание слишком длинное (макс. " + MAX_DESCRIPTION_LENGTH + " символов)");
        }
        this.description = description;
    }

    public void setManufacturer(String manufacturer) {
        if (manufacturer != null && manufacturer.trim().isEmpty()) {
            throw new IllegalArgumentException("Производитель не может быть пустой строкой");
        }
        this.manufacturer = manufacturer;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getSku() {
        return sku;
    }

    public double getDiscount() {
        return discount;
    }

    public double getRating() {
        return rating;
    }

    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public String getName() {
        return name;
    }
}
