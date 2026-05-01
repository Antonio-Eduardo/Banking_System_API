package ProgramaBanco;

import Banco_Contas.ContaEmpresarial;
import Banco_Contas.ContaPoupanca;
import Banco_Contas.Contas;

import java.util.List;

public class Programa {
    public static void main(String[] args) {

        Contas tudo = new Contas();
        Contas acc1 = new Contas("Eduardo", 1001, 0.0);
        ContaEmpresarial acc2 = new ContaEmpresarial("Lorena", 1002, 0.0, 500.0);
        Contas acc3 = new ContaEmpresarial("Bob", 1004, 0.0, 200.0);
        Contas acc4 = new ContaPoupanca("gareu", 1832, 0.0, 10.0);
        Contas acc5 = new Contas("Lipe", 3213, 400.00);
        Contas acc6 = new ContaEmpresarial("Marcao", 1123, 0.0, 1000.0);
        Contas acc7 = new Contas("Lia", 4212, 0.0);
        Contas acc8 = new ContaPoupanca("Bernardo", 1232, 0.0, 0.01);
        Contas acc9 = new ContaPoupanca("Fatima", 2351, 0.0, 0.02);

        tudo.addConta(acc1);
        tudo.addConta(acc2);
        tudo.addConta(acc3);
        tudo.addConta(acc4);
        tudo.addConta(acc5);
        tudo.addConta(acc6);
        tudo.addConta(acc7);
        tudo.addConta(acc8);
        tudo.addConta(acc9);

        List<ContaEmpresarial> soEmpresas = tudo.getEmpresas();
        List<ContaPoupanca> soPoupancas = tudo.getPoupancas();
        System.out.println("-----Todas as Contas-----");
        for (Contas c : tudo.getContas()){
            System.out.printf("Nome: %s | Identificacao: %d | Saldo: %.2f%n",c.getTitular(),c.getNumero(),c.getBalance());
        }
        System.out.println();
        System.out.println("-----Empresas-----");
        for (ContaEmpresarial c : soEmpresas){
            System.out.printf("Empresa: %s | Identificacao: %d | Saldo: %.2f | Emprestimo: %.2f%n",c.getTitular(),c.getNumero(),c.getBalance(),c.getEmprestimo());
        }
        System.out.println();
        System.out.println("-----Contas Poupanças-----");
        for (ContaPoupanca c : soPoupancas){
            System.out.printf("Titular: %s | Identificacao: %d | Saldo: %.2f | Juros: %.2f%n",c.getTitular(),c.getNumero(),c.getBalance(),c.getJuros());
        }
    }
}
