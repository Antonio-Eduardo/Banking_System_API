package com.eduardodev.banking_system_api.entities;

import com.eduardodev.banking_system_api.enums.TipoOperacao;
import com.eduardodev.banking_system_api.exceptions.LimiteExcedidoException;
import com.eduardodev.banking_system_api.exceptions.SaldoInsuficienteException;
import com.eduardodev.banking_system_api.interfaces.Tax;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public final class  ContaEmpresarial extends Conta implements Tax {
    @Column(name = "razao_social")
    private String razaoSocial;

    @Column(name = "cnpj")
    private String cnpj;

    @Column(name = "emprestimo")
    private BigDecimal emprestimo;

    @Override
    public Transacao sacar(BigDecimal valor){
        if (valor.add(tax(valor)).compareTo(balance) > 0) {
            throw new SaldoInsuficienteException("Saldo insuficiente para saque. O valor máximo permitido é R$20.000,00 (incluindo taxas).");
        }
        if (valor.compareTo(new BigDecimal("20000")) >= 0){
            throw new LimiteExcedidoException("Limite excedido para saque. O valor máximo permitido é R$20.000,00 (incluindo taxas).");
        }
        balance = balance.subtract(valor.add(tax(valor)));
        return addTransacao(new Transacao(TipoOperacao.OPERACAO_SAQUE, valor, balance));
    }
    @Override
    public Transacao deposito(BigDecimal valor){

        if (valor.add(tax(valor)).compareTo(new BigDecimal("5000")) > 0) {
            throw new LimiteExcedidoException("Limite excedido para depósito. O valor máximo permitido é R$5.000,00 (incluindo taxas).");
        }
        balance = balance.add(valor).subtract(tax(valor));
        return addTransacao(new Transacao(TipoOperacao.OPERACAO_DEPOSITO, valor, balance));
    }

    @Override
    public Transacao transferencia( BigDecimal valor, Conta contaDestino) {
        if (balance.compareTo(valor.add(tax(valor))) >= 0) {
            balance = balance.subtract(valor.add(tax(valor)));
            contaDestino.creditar(valor);

            contaDestino.addTransacao(new Transacao(TipoOperacao.OPERACAO_TRANSFERENCIA, valor, contaDestino.getBalance()));
            return addTransacao(new Transacao(TipoOperacao.OPERACAO_TRANSFERENCIA, valor, this.getBalance()));
        } else {
            throw new SaldoInsuficienteException("Saldo insuficiente para transferência. O valor máximo permitido é R$20.000,00 (incluindo taxas).");
        }
    }

    @Override
    public BigDecimal tax(BigDecimal valor) {
        return valor
                .multiply(new BigDecimal("0.02"))
                .setScale(2, RoundingMode.HALF_EVEN);
    }

}
