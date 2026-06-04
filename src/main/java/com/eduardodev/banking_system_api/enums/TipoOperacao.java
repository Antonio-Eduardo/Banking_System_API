package com.eduardodev.banking_system_api.enums;

import lombok.Data;
import lombok.Getter;


@Getter
public enum TipoOperacao {
    OPERACAO_DEPOSITO(1),
    OPERACAO_SAQUE(2),
    OPERACAO_TRANSFERENCIA(3);

    private final int value;

    TipoOperacao(int value) {
        this.value = value;
    }
    public static TipoOperacao fromValue(int value) {
        for (TipoOperacao tipo : TipoOperacao.values()) {
            if (tipo.getValue() == value) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Valor inválido para TipoOperacao: " + value);
    }

}
