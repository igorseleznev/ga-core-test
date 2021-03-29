package ru.seleznev.gacoretest.exception;

public class ClientUserNotFoundException extends RuntimeException {

    public ClientUserNotFoundException(String message) {
        super(message);
    }
}
