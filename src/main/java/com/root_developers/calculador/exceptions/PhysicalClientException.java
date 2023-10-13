package com.root_developers.calculador.exceptions;

public class PhysicalClientException extends RuntimeException {

    public PhysicalClientException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public PhysicalClientException(String message) {
        super(message);
    }

}
