package Banco_Contas;

import java.util.List;

public final class ContaPoupanca extends Contas{
    private double juros;

    public ContaPoupanca(){
        super();
    }

    public ContaPoupanca(String titular, Integer numero, double balance, double juros) {
        super(titular, numero, balance);
        this.juros = juros;
    }
    public void JurosValor(){
        balance += balance * juros;
    }

    public double getJuros() {
        return juros;
    }
@Override

    public void Saque(double valor) {
    balance -= valor;
}
    public void addPoupancaa(ContaPoupanca x){
        this.todasContas.add(x);
    }
    public void removePoupanca(ContaPoupanca x){
        this.todasContas.remove(x);
    }
    public List<Contas> getContasPoupanca() {
        return this.todasContas;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ContaPoupanca{");
        sb.append("juros=").append(juros);
        sb.append('}');
        return sb.toString();
    }
}
