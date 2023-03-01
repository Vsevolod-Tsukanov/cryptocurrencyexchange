package ru.relex.cryptocurrencyexchange.exception;

public class CalculatingException extends RuntimeException {
    public CalculatingException(String message) {
        super(message);
    }
}
