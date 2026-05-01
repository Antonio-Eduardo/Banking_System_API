package Banco_Contas;

import java.util.ArrayList;
import java.util.List;

public class Contas {
    private Integer numero;
    private String titular;
    protected double balance;
    List<Contas> todasContas = new ArrayList<>();

    public Contas() {
    }

    public Contas(String titular, Integer numero, double balance) {
        this.titular = titular;
        this.numero = numero;
        this.balance = balance;
    }

    public void Depositar(double deposito) {
        balance += deposito;
    }

    public void Saque(double saque) {
        if (saque <= balance) {
            balance -= saque + 5.0;
        } else {
            System.out.println("Saldo insuficiente !");
        }
    }

    public List<ContaEmpresarial> getEmpresas() { //filtrar empresas
        return todasContas.stream().filter(c -> c instanceof ContaEmpresarial).map(c -> (ContaEmpresarial) c).toList();
    }

    public List<ContaPoupanca> getPoupancas() {
        return todasContas.stream().filter(c -> c instanceof ContaPoupanca).map(c -> (ContaPoupanca) c).toList();
    }

    public List<Contas> getContas() {
        return todasContas;
    }

    public double getBalance() {
        return balance;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public void addConta(Contas conta) {
        todasContas.add(conta);
    }

    public void removeConta(Contas conta) {
        todasContas.remove(conta);
    }

    public List<Contas> getTodasContas() {
        return todasContas;
    }

    public void setTodasContas(List<Contas> todasContas) {
        this.todasContas = todasContas;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Contas{");
        sb.append("titular='").append(titular).append('\'');
        sb.append(", numero=").append(numero);
        sb.append(", balance=").append(balance);
        sb.append('}');
        return sb.toString();
    }
}
