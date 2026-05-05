package Banco_Contas;

import Excecoes.SaldoInsuficienteException;

import java.util.ArrayList;
import java.util.List;

public abstract class Contas {
    private Integer numero;
    private String titular;
    protected double balance;
    List<Transacao> historicoTransacoes = new ArrayList<>();

    public Contas() {
    }
    public void addTransacao(Transacao transacao){
        historicoTransacoes.add(transacao);
    }
    public Contas(String titular, Integer numero, double balance) {
        this.titular = titular;
        this.numero = numero;
        this.balance = balance;
    }
    public double getBalance() {
        return balance;
    }

    public abstract void sacar(double valor);
    public abstract void deposito(double valor);

    public Integer getNumero() {
        return numero;
    }

    public String getTitular() {
        return titular;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("titular= ").append(titular).append('\'');
        sb.append("\nnumero= ").append(numero);
        sb.append("\nSaldo= ").append(balance);
        sb.append("\n--- Transacoes ---\n");
        for (Transacao f : historicoTransacoes){
            sb.append("\n").append(f);
        }
        return sb.toString();
    }
}
