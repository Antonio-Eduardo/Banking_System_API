package Banco_Contas;

import ENUM.TipoOperacao;
import Excecoes.LimiteExcedidoException;
import Excecoes.SaldoInsuficienteException;

public final class ContaPoupanca extends Contas {
    private static final double TAXA_JUROS = 1.1;

    public ContaPoupanca() {
        super();
    }

    public ContaPoupanca(String titular, Integer numero, double balance) {
        super(titular, numero, balance);

    }

    @Override
    public void sacar(double valor){
        if (balance < valor) {
            throw new SaldoInsuficienteException();
        }
        balance -= valor;
        addTransacao(new Transacao(TipoOperacao.OPERACAO_SAQUE, valor, balance));
    }
    @Override
    public void deposito(double valor){
        if (valor > 10000) {
            throw new LimiteExcedidoException();
        }
        balance += valor;
        addTransacao(new Transacao(TipoOperacao.OPERACAO_DEPOSITO, valor, balance));
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
    public String toString() {
        final StringBuilder sb = new StringBuilder("Contas [Poupanca] {");
        sb.append(super.toString());
        sb.append(", rendimento previsto= ").append(balance * TAXA_JUROS);
        sb.append('}');
        return sb.toString();
    }
}
