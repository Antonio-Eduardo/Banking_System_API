package Conta;

public class Contas {
    private Integer numero;
    private String titular;
    private double balance;

    public Contas(){}

    public Contas(String titular, Integer numero, double balance) {
        this.titular = titular;
        this.numero = numero;
        this.balance = balance;
    }

    public void Depositar(double deposito){
        balance += deposito;
    }
    public void Saque(double saque){
        if (saque <= balance){
            balance -= saque;
        } else{
            System.out.println("Saldo insuficiente !");
        }
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
}
