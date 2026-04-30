package Banco_Contas;

public class ContaEmpresarial extends Contas{
    private double emprestimo;

    ContaEmpresarial(){
        super();
    }

    public ContaEmpresarial(String titular, Integer numero, double balance, double emprestimo) {
        super(titular, numero, balance);
        this.emprestimo = emprestimo;
    }
    public void Emprestimo(double valor){
        emprestimo += valor;
    }

    public double getEmprestimo() {
        return emprestimo;
    }

}
