package com.eduardodev.banking_system_api.exceptions;

import com.eduardodev.banking_system_api.enums.ErrorCode;

public class ValidacaoException extends RuntimeException{
    private final ErrorCode codigo;

    public ValidacaoException(ErrorCode codigo, String msg){
        super(msg);
        this.codigo = codigo;
    }
    public ErrorCode getError(){
        return codigo;
    }
}
