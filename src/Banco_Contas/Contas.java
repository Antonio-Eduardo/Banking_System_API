package Banco_Contas;

import java.util.ArrayList;
import java.util.List;

public abstract class Contas {
    private Integer numero;
    private String titular;
    protected double balance;

    public Contas() {
    }

    public Contas(String titular, Integer numero, double balance) {
        this.titular = titular;
        this.numero = numero;
        this.balance = balance;
    }

    public abstract void Depositar(double deposito);

    public abstract void Saque(double saque);

    public double getBalance() {
        return balance;
    }

    public Integer getNumero() {
        return numero;
    }

    public String getTitular() {
        return titular;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("titular='").append(titular).append('\'');
        sb.append(", numero=").append(numero);
        sb.append(", balance=").append(balance);
        return sb.toString();
    }
}
