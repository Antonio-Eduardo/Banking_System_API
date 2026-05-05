package Banco_Contas;

import ENUM.TipoOperacao;
import Excecoes.LimiteExcedidoException;
import Excecoes.SaldoInsuficienteException;

import java.util.Scanner;

public class ContaCorrente extends Contas {
    public ContaCorrente() {
        super();
    }

    public ContaCorrente(String titular, Integer numero, double balance) {
        super(titular, numero, balance);
    }

    @Override
    public void sacar(double valor){
        double taxaCorrente = 25.00;
        if (balance < valor + taxaCorrente) {
            throw new SaldoInsuficienteException();
        }
        if (valor >= 20000){
            throw new LimiteExcedidoException();
        }
        balance -= valor + taxaCorrente;
        addTransacao(new Transacao(TipoOperacao.OPERACAO_SAQUE, valor, balance));
    }
    @Override
    public void deposito(double valor){
        double taxaCorrente = 25.00;
        if (taxaCorrente+ valor > 25000) {
            throw new LimiteExcedidoException();
        }
        balance += valor - taxaCorrente;
        addTransacao(new Transacao(TipoOperacao.OPERACAO_DEPOSITO, valor, balance));
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Conta [Corrente] {");
        sb.append(" Nome: ").append(super.getTitular());
        sb.append(" Numero: ").append(super.getNumero());
        sb.append(" Saldo: ").append(balance).append("}");
        sb.append(super.toString());
        return sb.toString();
    }
}
