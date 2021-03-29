package ru.seleznev.gacoretest.exception;

public class WrongClientPasswordException extends RuntimeException {

    public WrongClientPasswordException(String message) {
        super(message);
    }
}
