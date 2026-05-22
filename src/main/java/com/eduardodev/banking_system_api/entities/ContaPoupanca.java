package com.eduardodev.banking_system_api.entities;

import com.eduardodev.banking_system_api.enums.TipoOperacao;
import com.eduardodev.banking_system_api.exceptions.LimiteExcedidoException;
import com.eduardodev.banking_system_api.exceptions.SaldoInsuficienteException;
import com.eduardodev.banking_system_api.interfaces.Tax;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public final class ContaPoupanca extends Conta implements Tax {
    private static final double JUROS_RENDIMENTO = 0.008;

    @Override
    public void sacar(double valor){
        if (balance < valor + tax(valor)) {
            throw new SaldoInsuficienteException();
        }
        balance -= valor + tax(valor);
        addTransacao(new Transacao(TipoOperacao.OPERACAO_SAQUE, valor, balance));
    }
    @Override
    public void deposito(double valor){
        if (tax(valor) + valor > 10000) {
            throw new LimiteExcedidoException();
        }
        balance += valor - tax(valor);
        addTransacao(new Transacao(TipoOperacao.OPERACAO_DEPOSITO, valor, balance));
    }

    @Override
    public void transferencia( Double valor, Conta contaDestino) {
        if (balance >= valor + tax(valor)) {
            balance -= valor + tax(valor);
            contaDestino.creditar(valor);
            addTransacao(new Transacao(TipoOperacao.OPERACAO_TRANSFERENCIA, valor, this.getBalance()));
            contaDestino.addTransacao(new Transacao(TipoOperacao.OPERACAO_TRANSFERENCIA, valor, contaDestino.getBalance()));
        } else {
            throw new SaldoInsuficienteException();
        }
    }
    public double getRendimento(){
        return balance * JUROS_RENDIMENTO;
    }
    @Override
    public double tax(double valor) {
        return valor * 0.005;
    }

}
