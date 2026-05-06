package Banco_Contas;

import Excecoes.SaldoInsuficienteException;

import java.util.ArrayList;
import java.util.List;

public abstract class Contas {
    private String idConta;
    private String titular;
    protected double balance;
    List<Transacao> historicoTransacoes = new ArrayList<>();

    public Contas() {
    }
    public void addTransacao(Transacao transacao){
        historicoTransacoes.add(transacao);
    }
    public Contas(String titular, String idConta, double balance) {
        this.titular = titular;
        this.idConta = idConta;
        this.balance = balance;
    }
    public double getBalance() {
        return balance;
    }

    public abstract void sacar(double valor,String id);
    public abstract void deposito(double valor, String id);

    public String getIdConta() {
        return idConta;
    }

    public String getTitular() {
        return titular;
    }
    public Transacao getUltimaTransacao() {
        return historicoTransacoes.get(historicoTransacoes.size() - 1);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("titular= ").append(titular).append('\'');
        sb.append("\nnumero= ").append(idConta);
        sb.append("\nSaldo= ").append(balance);
        sb.append("\n--- Transacoes ---");
        for (Transacao f : historicoTransacoes){
            sb.append("\n").append(f);
        }
        sb.append("");
        return sb.toString();
    }
}
