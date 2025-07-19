package com.experiment.daeseda_renewal.global.exception;

public class AddressException extends RuntimeException {

    public AddressException(String message) {
        super(message);
    }

    public AddressException(String message, Throwable cause) {
        super(message, cause);
    }
}
