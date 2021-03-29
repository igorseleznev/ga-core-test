package ru.seleznev.gacoretest.exception;

public class LoginAlreadyExistsException extends RuntimeException {

    public LoginAlreadyExistsException(String message) {
        super(message);
    }
}
