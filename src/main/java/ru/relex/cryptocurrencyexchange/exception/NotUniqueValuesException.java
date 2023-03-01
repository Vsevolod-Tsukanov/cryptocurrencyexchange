package ru.relex.cryptocurrencyexchange.exception;

public class NotUniqueValuesException extends RuntimeException {
    public NotUniqueValuesException(String message) {
        super(message);
    }
}
