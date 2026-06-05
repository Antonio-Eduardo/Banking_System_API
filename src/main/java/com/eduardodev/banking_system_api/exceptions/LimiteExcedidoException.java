package com.eduardodev.banking_system_api.exceptions;

import com.eduardodev.banking_system_api.enums.ErrorCode;

public class LimiteExcedidoException extends RuntimeException {

   public LimiteExcedidoException(String message) {
        super(message);
    }
}
