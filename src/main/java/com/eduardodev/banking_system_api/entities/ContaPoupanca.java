package com.eduardodev.banking_system_api.entities;

import com.eduardodev.banking_system_api.enums.TipoOperacao;
import com.eduardodev.banking_system_api.exceptions.LimiteExcedidoException;
import com.eduardodev.banking_system_api.exceptions.SaldoInsuficienteException;
import com.eduardodev.banking_system_api.interfaces.Tax;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public final class ContaPoupanca extends Conta implements Tax {
    private static final double JUROS_RENDIMENTO = 0.008;

    @Column(name = "data_aniversario")
    private LocalDate dataAniversario;

    @Override
    public Transacao sacar(BigDecimal valor){
        if (valor.add(tax(valor)).compareTo(balance) > 0) {
            throw new SaldoInsuficienteException("Saldo insuficiente para saque. O valor máximo permitido é R$10.000,00 (incluindo taxas).");
        }
        balance = balance.subtract(valor.add(tax(valor)));
        return addTransacao(new Transacao(TipoOperacao.OPERACAO_SAQUE, valor, balance, this));
    }
    @Override
    public Transacao deposito(BigDecimal valor){
        if (tax(valor).add(valor).compareTo(new BigDecimal("10000")) > 0) {
            throw new LimiteExcedidoException("Limite excedido para depósito. O valor máximo permitido é R$10.000,00 (incluindo taxas).");
        }
        balance = balance.add(valor).subtract(tax(valor));
        return addTransacao(new Transacao(TipoOperacao.OPERACAO_DEPOSITO, valor, balance, this));
    }

    @Override
    public Transacao transferencia( BigDecimal valor, Conta contaDestino) {
        if (valor.add( tax(valor) ).compareTo(balance) <= 0) {
            balance = balance.subtract(valor.add(tax(valor)));
            contaDestino.creditar(valor);
             contaDestino.addTransacao(new Transacao(TipoOperacao.OPERACAO_TRANSFERENCIA, valor, contaDestino.getBalance(), contaDestino));
            return addTransacao(new Transacao(TipoOperacao.OPERACAO_TRANSFERENCIA, valor, this.getBalance(), this));
        } else {
            throw new SaldoInsuficienteException("Saldo insuficiente para transferência. O valor máximo permitido é R$10.000,00 (incluindo taxas).");
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
