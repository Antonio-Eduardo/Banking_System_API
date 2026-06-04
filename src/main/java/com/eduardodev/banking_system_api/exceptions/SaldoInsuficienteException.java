package com.eduardodev.banking_system_api.exceptions;

public class SaldoInsuficienteException extends RuntimeException {

    public SaldoInsuficienteException(String msg) {
        super(msg);
    }
}
