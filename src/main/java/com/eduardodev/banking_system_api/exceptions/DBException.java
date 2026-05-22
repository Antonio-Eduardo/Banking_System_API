package com.eduardodev.banking_system_api.exceptions;

import com.eduardodev.banking_system_api.enums.ErrorCode;

public class DBException extends  ValidacaoException{
    public DBException() {
        super(ErrorCode.DB_EXCEPTION, "Erro no DB");
    }
}
