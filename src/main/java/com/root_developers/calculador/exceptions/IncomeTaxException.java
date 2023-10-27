package com.root_developers.calculador.exceptions;

public class IncomeTaxException extends RuntimeException {

    public IncomeTaxException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public IncomeTaxException(String message) {
        super(message);
    }

}
