package ru.relex.cryptocurrencyexchange.exception;

public class NoEntityFoundException extends RuntimeException {
    public NoEntityFoundException(String message) {
        super(message);
    }

}
