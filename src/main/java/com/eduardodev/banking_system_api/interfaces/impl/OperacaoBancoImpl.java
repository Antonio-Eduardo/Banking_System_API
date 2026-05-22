package com.eduardodev.banking_system_api.interfaces.impl;

import com.eduardodev.banking_system_api.entities.Conta;
import com.eduardodev.banking_system_api.exceptions.DBException;
import com.eduardodev.banking_system_api.interfaces.OperacaoBanco;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;

public class OperacaoBancoImpl implements OperacaoBanco {
    private EntityManager em;

    public OperacaoBancoImpl(EntityManager em) {
        this.em = em;

    }
    public void executarEmTransacao(Runnable operacao){
        try {
            em.getTransaction().begin();
            operacao.run();
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            if (em.getTransaction() != null && em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            throw new DBException();
        }
    }
    public void processDeposito(Conta conta, double valor) {
        executarEmTransacao(()-> {
            Conta contaGerenciada = em.merge(conta);
            contaGerenciada.deposito(valor);
        });
    }
    public void processSaque(Conta conta, double valor) {
        executarEmTransacao(()-> {
            Conta contaGerenciada = em.merge(conta);
            contaGerenciada.sacar(valor);
        });
    }
    public void processTransferencia(Conta contaOrigem, Double valorT, Conta contaDestino){
        executarEmTransacao(()-> {
            Conta origemGerenciada = em.merge(contaOrigem);
            Conta destinoGerenciada = em.merge(contaDestino);
            origemGerenciada.transferencia(valorT, destinoGerenciada);
        });
    }
}
