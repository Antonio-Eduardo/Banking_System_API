package com.eduardodev.banking_system_api.entities;

import com.eduardodev.banking_system_api.enums.TipoOperacao;
import com.eduardodev.banking_system_api.exceptions.LimiteExcedidoException;
import com.eduardodev.banking_system_api.exceptions.SaldoInsuficienteException;
import com.eduardodev.banking_system_api.interfaces.Tax;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Entity
@NoArgsConstructor
public class ContaCorrente extends Conta implements Tax {

    @Override
    public void sacar(BigDecimal valor){
        BigDecimal taxaCorrente = new BigDecimal("25.00");
        if (balance.compareTo(valor.add(taxaCorrente)) < 0) {
            throw new SaldoInsuficienteException();
        }
        if (valor.compareTo(new BigDecimal("20000")) >= 0){
            throw new LimiteExcedidoException();
        }
        balance = balance.subtract(valor.add(taxaCorrente));
        addTransacao(new Transacao(TipoOperacao.OPERACAO_SAQUE, valor, balance));
    }
    @Override
    public void deposito(BigDecimal valor){
        if (tax(valor).add(valor).compareTo(new BigDecimal("2500"))>0) {
            throw new LimiteExcedidoException();
        }
        balance = balance.add(valor).subtract(tax(valor));
        addTransacao(new Transacao(TipoOperacao.OPERACAO_DEPOSITO, valor, balance));
    }
    @Override
    public void transferencia(BigDecimal valor, Conta contaDestino) {
        if (balance.compareTo(valor.add(tax(valor))) >= 0) {
            balance = balance.subtract(valor.add(tax(valor)));
            contaDestino.creditar(valor);

            addTransacao(new Transacao(TipoOperacao.OPERACAO_TRANSFERENCIA, valor, this.getBalance()));
            contaDestino.addTransacao(new Transacao(TipoOperacao.OPERACAO_TRANSFERENCIA, valor, contaDestino.getBalance()));
        } else {
            throw new SaldoInsuficienteException();
        }
    }

    @Override
    public BigDecimal tax(BigDecimal valor) {
        return valor.multiply(BigDecimal.valueOf(0.02));
    }

}
