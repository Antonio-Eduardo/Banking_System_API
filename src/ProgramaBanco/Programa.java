package ProgramaBanco;

import Banco_Contas.ContaEmpresarial;
import Banco_Contas.ContaPoupanca;
import Banco_Contas.Contas;

public class Programa {
    public static void main(String[] args) {

        Contas tudo = new Contas();
        ContaEmpresarial contasEmpresa = new ContaEmpresarial();
        ContaPoupanca contaPoupancas = new ContaPoupanca();
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

        for (Contas x : tudo.getTodasContas()) {
            if (x instanceof ContaEmpresarial) {
                contasEmpresa.addEmpresa((ContaEmpresarial) x);
            }
            if (x instanceof ContaPoupanca) {
                contaPoupancas.addPoupancaa((ContaPoupanca) x);
            }
        }
        for (Contas x : contasEmpresa.getContasEmpresa()){
            System.out.printf("%s %d %.2f%n",x.getTitular(),x.getNumero(),x.getBalance());
        }
        System.out.println();

        for (Contas x : contaPoupancas.getContasPoupanca()){
            System.out.printf("%s %d %.2f%n",x.getTitular(),x.getNumero(),x.getBalance());
        }
    }
}
