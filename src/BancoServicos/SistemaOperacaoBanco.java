package BancoServicos;

import Banco_Contas.*;
import Excecoes.LimiteExcedidoException;
import Excecoes.SaldoInsuficienteException;

public class SistemaOperacaoBanco {
    private ServicoTaxaConta tax;
    private double amount;

    public SistemaOperacaoBanco(double amount) {
        this.amount = amount;
        this.tax = new ServicoTaxaConta();
    }
    public void processDeposito(Contas conta, double valor){
       conta.deposito(valor);
    }
    public void processSaque(Contas conta, double valor){
        conta.sacar(valor);
    }
    private double buscarTaxa(Contas conta){
        if (conta instanceof ContaCorrente){return tax.taxaCorrente();}
        if (conta instanceof ContaEmpresarial) {return tax.taxaEmpresa();}
        if (conta instanceof ContaPoupanca) {return tax.taxaPoupanca();}
        return 0.0;
    }
    public double getSaldo() {
        return amount;
    }

    public void setSaldo(double saldo) {
        this.amount = saldo;
    }

    public ServicoTaxaConta getTax() {
        return tax;
    }

    public void setTax(ServicoTaxaConta tax) {
        this.tax = tax;
    }
}
