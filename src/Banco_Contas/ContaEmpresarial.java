package Banco_Contas;

import ENUM.TipoOperacao;
import Excecoes.LimiteExcedidoException;
import Excecoes.SaldoInsuficienteException;

public final class  ContaEmpresarial extends Contas{
    private double emprestimo;

    public ContaEmpresarial(){
        super();
    }

    public ContaEmpresarial(String titular, Integer numero, double balance, double emprestimo) {
        super(titular, numero, balance);
        this.emprestimo = emprestimo;
    }
    @Override
    public void sacar(double valor){
        double taxaEmpresa = 50.00;
        if (balance < valor + taxaEmpresa) {
            throw new SaldoInsuficienteException();
        }
        if (valor > 20000){
            throw new LimiteExcedidoException();
        }
        balance -= valor + taxaEmpresa;
        addTransacao(new Transacao(TipoOperacao.OPERACAO_SAQUE, valor, balance));
    }
    @Override
    public void deposito(double valor){
        double taxaEmpresa = 50.00;
        if (taxaEmpresa + valor > 35000) {
            throw new LimiteExcedidoException();
        }
        balance += valor - taxaEmpresa;
        addTransacao(new Transacao(TipoOperacao.OPERACAO_DEPOSITO, valor, balance));
    }
    public void addEmprestimo(double valor){
        emprestimo += valor;
    }

    public double getEmprestimo() {
        return emprestimo;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Conta [Empresa] {");
        sb.append(super.toString());
        sb.append(" emprestimo= ").append(emprestimo);
        sb.append('}');
        return sb.toString();
    }
}
