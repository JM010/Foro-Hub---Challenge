package com.JCservicios.forohub.domain.execption;

public class ValidationExecption extends RuntimeException {
    public ValidationExecption(String message) {
        super(message);
    }
}
