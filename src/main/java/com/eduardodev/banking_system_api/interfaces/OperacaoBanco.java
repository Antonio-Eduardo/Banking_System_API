package com.eduardodev.banking_system_api.interfaces;

import com.eduardodev.banking_system_api.entities.Conta;

import java.math.BigDecimal;

public interface OperacaoBanco {
    public void processDeposito(Conta conta, BigDecimal valor);
    public void processSaque(Conta conta, BigDecimal valor);
    public void processTransferencia(Conta contaOrigem, BigDecimal valorT, Conta contaDestino);
}
