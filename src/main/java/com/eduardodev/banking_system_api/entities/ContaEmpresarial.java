package com.eduardodev.banking_system_api.entities;

import com.eduardodev.banking_system_api.enums.TipoOperacao;
import com.eduardodev.banking_system_api.exceptions.LimiteExcedidoException;
import com.eduardodev.banking_system_api.exceptions.SaldoInsuficienteException;
import interfaces.Tax;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public final class  ContaEmpresarial extends Conta implements Tax {
    private double emprestimo;

    @Override
    public void sacar(double valor){
        double taxaEmpresa = 50.00;
        if (balance < valor + taxaEmpresa) {
            throw new SaldoInsuficienteException();
        }
        if (valor > 20000){
            throw new LimiteExcedidoException();
        }
        balance -= valor + taxaEmpresa;
        addTransacao(new Transacao(TipoOperacao.OPERACAO_SAQUE, valor, balance));
    }
    @Override
    public void deposito(double valor){

        if (tax(valor) + valor > 35000) {
            throw new LimiteExcedidoException();
        }
        balance += valor - tax(valor);
        addTransacao(new Transacao(TipoOperacao.OPERACAO_DEPOSITO, valor, balance));;
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

    @Override
    public double tax(double valor) {
        return valor * 0.07;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("\nConta [Empresa]\n");
        sb.append(super.toString());
        sb.append("\nEmprestimo= ").append(emprestimo);
        return sb.toString();
    }
}
