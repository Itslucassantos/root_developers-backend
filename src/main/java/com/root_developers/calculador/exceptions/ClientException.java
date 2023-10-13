package com.root_developers.calculador.exceptions;

public class ClientException extends RuntimeException{

    public ClientException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public ClientException(String message) {
        super(message);
    }
}
