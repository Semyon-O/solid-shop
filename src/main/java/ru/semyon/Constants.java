package ru.semyon;

public class Constants {

    // <--- USER --->

    ///
    /// Принцип DRY
    /// Все константы вынесены в единое место
    ///

    public static final int MIN_PASSWORD_LENGTH = 8;

    public static final int MAX_LOGIN_LENGTH = 20;

    public static final String REGEX_LOGIN = "[a-zA-Z0-9_]+";

    public static final String REGEX_PASSWORD = ".*[a-zA-Z].*";

    public static final Integer EMAIL_MAX_LENGTH = 254;

    // <-- ADMIN -->

    public static final Integer SET_ACCESS_LEVEL = 3;

    // <-- Product -->

    public static final int MAX_NAME_LENGTH = 100;
    public static final int MAX_DESCRIPTION_LENGTH = 500;
    public static final double MIN_PRICE = 0.01;
    public static final double MAX_PRICE = 1_000_000;
    public static final int MIN_STOCK = 0;
    public static final double MIN_DISCOUNT = 0;
    public static final double MAX_DISCOUNT = 100;
    public static final double MIN_RATING = 0;
    public static final double MAX_RATING = 5;

}
