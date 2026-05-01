package Banco_Contas;

import java.util.List;

public final class ContaPoupanca extends Contas {
    private static final double TAXA_JUROS = 1.1;

    public ContaPoupanca() {
        super();
    }

    public ContaPoupanca(String titular, Integer numero, double balance) {
        super(titular, numero, balance);

    }

    public double rendimentoTotal() {
       return balance *= TAXA_JUROS;
    }

    public double attSaldo(){
        return balance *= TAXA_JUROS;
    }

    public double getJuros() {
        return TAXA_JUROS;
    }

    @Override

    public void Saque(double valor) {
        if (valor <= balance){
        balance -= valor;}
        else {
            System.out.println("Saldo insuficiente");
        }
    }
    @Override
    public void Depositar(double valor){
        balance += valor;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Contas [Poupanca] {");
        sb.append("titular='").append(getTitular()).append('\'');
        sb.append(", numero=").append(getNumero());
        sb.append(", saldo=").append(getBalance());
        sb.append(", rendimento previsto=").append(balance * TAXA_JUROS);
        sb.append('}');
        return sb.toString();
    }
}
