package com.eduardodev.banking_system_api.entities;

import com.eduardodev.banking_system_api.enums.TipoOperacao;
import com.eduardodev.banking_system_api.exceptions.LimiteExcedidoException;
import com.eduardodev.banking_system_api.exceptions.SaldoInsuficienteException;
import com.eduardodev.banking_system_api.interfaces.Tax;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@NoArgsConstructor
public final class ContaPoupanca extends Conta implements Tax {
    private static final double JUROS_RENDIMENTO = 0.008;

    @Override
    public void sacar(BigDecimal valor){
        if (valor.add(tax(valor)).compareTo(balance) > 0) {
            throw new SaldoInsuficienteException();
        }
        balance = balance.subtract(valor.add(tax(valor)));
        addTransacao(new Transacao(TipoOperacao.OPERACAO_SAQUE, valor, balance));
    }
    @Override
    public void deposito(BigDecimal valor){
        if (tax(valor).add(valor).compareTo(new BigDecimal("10000")) > 0) {
            throw new LimiteExcedidoException();
        }
        balance = balance.add(valor).subtract(tax(valor));
        addTransacao(new Transacao(TipoOperacao.OPERACAO_DEPOSITO, valor, balance));
    }

    @Override
    public void transferencia( BigDecimal valor, Conta contaDestino) {
        if (valor.add( tax(valor) ).compareTo(balance) <= 0) {
            balance = balance.subtract(valor.add(tax(valor)));
            contaDestino.creditar(valor);
            addTransacao(new Transacao(TipoOperacao.OPERACAO_TRANSFERENCIA, valor, this.getBalance()));
            contaDestino.addTransacao(new Transacao(TipoOperacao.OPERACAO_TRANSFERENCIA, valor, contaDestino.getBalance()));
        } else {
            throw new SaldoInsuficienteException();
        }
    }
    public BigDecimal getRendimento(){
        return balance.multiply(
                BigDecimal.valueOf(JUROS_RENDIMENTO)
        );
    }
    @Override
    public BigDecimal tax(BigDecimal valor) {
        return valor
                .multiply(new BigDecimal("0.02"))
                .setScale(2, RoundingMode.HALF_EVEN);
    }

}
