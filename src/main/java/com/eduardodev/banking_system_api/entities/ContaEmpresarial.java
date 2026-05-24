package com.eduardodev.banking_system_api.entities;

import com.eduardodev.banking_system_api.enums.TipoOperacao;
import com.eduardodev.banking_system_api.exceptions.LimiteExcedidoException;
import com.eduardodev.banking_system_api.exceptions.SaldoInsuficienteException;
import com.eduardodev.banking_system_api.interfaces.Tax;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public final class  ContaEmpresarial extends Conta implements Tax {
    private BigDecimal emprestimo;

    @Override
    public void sacar(BigDecimal valor){
        if (valor.add(tax(valor)).compareTo(balance) > 0) {
            throw new SaldoInsuficienteException();
        }
        if (valor.compareTo(new BigDecimal("20000")) >= 0){
            throw new LimiteExcedidoException();
        }
        balance = balance.subtract(valor.add(tax(valor)));
        addTransacao(new Transacao(TipoOperacao.OPERACAO_SAQUE, valor, balance));
    }
    @Override
    public void deposito(BigDecimal valor){

        if (valor.add(tax(valor)).compareTo(new BigDecimal("5000")) > 0) {
            throw new LimiteExcedidoException();
        }
        balance = balance.add(valor).subtract(tax(valor));
        addTransacao(new Transacao(TipoOperacao.OPERACAO_DEPOSITO, valor, balance));;
    }

    public BigDecimal getEmprestimoPers() {
        return emprestimo;
    }

    public void setEmprestimoPers(BigDecimal emprestimo) {
        this.emprestimo = emprestimo;
    }

    @Override
    public void transferencia( BigDecimal valor, Conta contaDestino) {
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
        return valor
                .multiply(new BigDecimal("0.02"))
                .setScale(2, RoundingMode.HALF_EVEN);
    }

}
