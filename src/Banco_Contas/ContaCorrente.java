package Banco_Contas;

public class ContaCorrente extends Contas{
    public ContaCorrente(){super();}

    public ContaCorrente(String titular, Integer numero, double balance) {
        super(titular, numero, balance);
    }
    @Override
    public void Saque(double valor) {
        if (valor <= balance){
        double taxa = valor +3.00;
        balance -= taxa;}
        else {
            System.out.println("Saldo invalido");
        }
    }
    @Override
    public void Depositar(double valor){
        balance += valor;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Conta [Corrente] {");
        sb.append(" Nome: ").append(super.getTitular());
        sb.append(" Numero: ").append(super.getNumero());
        sb.append(" Saldo").append(balance).append("}");
        return sb.toString();
    }
}
