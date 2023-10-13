package com.root_developers.calculador.exceptions;

public class LegalClientException extends RuntimeException {

    public LegalClientException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public LegalClientException(String message) {
        super(message);
    }

}
