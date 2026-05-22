package com.eduardodev.banking_system_api.interfaces.impl;

import com.eduardodev.banking_system_api.entities.Conta;
import com.eduardodev.banking_system_api.exceptions.DBException;
import com.eduardodev.banking_system_api.interfaces.OperacaoBanco;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;

import java.math.BigDecimal;

public class OperacaoBancoImpl implements OperacaoBanco {


    public void processDeposito(Conta conta, BigDecimal valor) {
            conta.deposito(valor);
    }
    public void processSaque(Conta conta, BigDecimal valor) {
            conta.sacar(valor);
    }
    public void processTransferencia(Conta contaOrigem, BigDecimal valorT, Conta contaDestino){
            contaOrigem.transferencia(valorT, contaDestino);
    }
}
