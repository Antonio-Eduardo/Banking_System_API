package Banco_Contas;

public class ContaPoupança extends Contas{
    private double juros;

    public ContaPoupança(){
        super();
    }

    public ContaPoupança(String titular, Integer numero, double balance, double juros) {
        super(titular, numero, balance);
        this.juros = juros;
    }
    public void JurosValor(){
        balance += balance * juros;
    }

    public double getJuros() {
        return juros;
    }

}
