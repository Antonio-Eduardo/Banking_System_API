package Banco_Contas;

import ENUM.TipoOperacao;
import Excecoes.LimiteExcedidoException;
import Excecoes.SaldoInsuficienteException;
import Services.Tax;

import java.util.UUID;

public final class ContaPoupanca extends Contas implements Tax {
    private static final double JUROS_RENDIMENTO = 0.008;
    public ContaPoupanca(String titular, String idConta, double balance) {
        super(titular, idConta, balance);
    }
    @Override
    public void sacar(double valor, String id){
        if (balance < valor) {
            throw new SaldoInsuficienteException();
        }
        balance -= valor + tax(valor);
        addTransacao(new Transacao(TipoOperacao.OPERACAO_SAQUE, valor, balance,id));
    }
    @Override
    public void deposito(double valor, String id){
        if (tax(valor) + valor > 10000) {
            throw new LimiteExcedidoException();
        }
        balance += valor - tax(valor);
        addTransacao(new Transacao(TipoOperacao.OPERACAO_DEPOSITO, valor, balance,id));
    }
    @Override
    public double tax(double valor) {
        return valor * 0.005;
    }
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("\nContas [Poupanca]\n");
        sb.append(super.toString());
        sb.append("\nrendimento previsto= ").append(balance * JUROS_RENDIMENTO);

        return sb.toString();
    }
}
