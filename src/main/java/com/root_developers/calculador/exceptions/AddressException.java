package com.root_developers.calculador.exceptions;

public class AddressException extends RuntimeException {

    public AddressException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public AddressException(String message) {
        super(message);
    }
}
