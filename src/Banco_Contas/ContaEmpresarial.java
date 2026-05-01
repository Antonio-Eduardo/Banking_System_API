package Banco_Contas;

import java.util.List;

public final class  ContaEmpresarial extends Contas{
    private double emprestimo;

    public ContaEmpresarial(){
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
    public void addEmpresa(ContaEmpresarial x){
        this.todasContas.add(x);
    }
    public void removeEmpresa(ContaEmpresarial x){
        this.todasContas.remove(x);
    }

    @Override
    public void Saque(double valor) {
        super.Saque(valor);
        balance -= 2.0;
    }
    public List<Contas> getContasEmpresa() {
        return this.todasContas;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ContaEmpresarial{");
        sb.append("emprestimo=").append(emprestimo);
        sb.append('}');
        return sb.toString();
    }
}
