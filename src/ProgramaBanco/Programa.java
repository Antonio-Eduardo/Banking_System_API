package ProgramaBanco;

import Banco_Contas.ContaCorrente;
import Banco_Contas.ContaEmpresarial;
import Banco_Contas.ContaPoupanca;
import Banco_Contas.Contas;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Programa {
    public static void main(String[] args) {
        List<Contas> todasContas = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        System.out.print("Quantidade de contas a serem registradas: ");
        int quantidade = sc.nextInt();
        int limite = 0;
        sc.nextLine();
        int opcao;
        do {
            System.out.println("Selecione o tipo de conta (1-Conta corrente|2-Conta empresarial|3-Conta poupanca)");
            opcao = sc.nextInt();
            sc.nextLine();
            switch(opcao){
                case 1:
                    System.out.print("Nome do titular: ");
                    String nome = sc.nextLine();
                    System.out.print("iD da conta: ");
                    int numero = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Saldo inicial : 0,00");
                    double saldo = 0.00;
                    Contas accCorrente = new ContaCorrente(nome,numero,saldo);
                    todasContas.add(accCorrente);
                    limite++;
                    break;
                case 2:
                    System.out.print("Nome do titular: ");
                    String nomeEmp = sc.nextLine();
                    System.out.print("iD da conta: ");
                    int numeroEmp = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Saldo inicial : 0,00");
                    double saldoEmp = 0.00;
                    System.out.print("Emprestimo inicial: ");
                    double emprestimo = sc.nextDouble();
                    Contas accEmp = new ContaEmpresarial(nomeEmp,numeroEmp,saldoEmp,emprestimo);
                    todasContas.add(accEmp);
                    limite++;
                    break;
                case 3:
                    System.out.print("Nome do titular: ");
                    String nomePoup = sc.nextLine();
                    System.out.print("iD da conta: ");
                    int numeroPoup = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Saldo inicial : 0,00");
                    double saldoPoup = 0.00;
                    Contas accPoup = new ContaPoupanca(nomePoup,numeroPoup,saldoPoup);
                    todasContas.add(accPoup);
                    limite++;
                    break;
            }
        }while (limite < quantidade);


        for (Contas f : todasContas){
            System.out.println(f);
        }
        int opcao2;
        do {
            System.out.println("1-Deposito | 2-Saque | 3-sair");
            opcao2 = sc.nextInt();
            sc.nextLine();

            System.out.println("Digite o numero da conta que deseja realizar o deposito: ");
            int numeroConta = sc.nextInt();
            sc.nextLine();
            Contas procura = todasContas.stream().filter(c -> c.getNumero().equals(numeroConta)).findFirst().orElse(null);

            if (procura == null) {
                System.out.println("numero invalido;");
                opcao2 = 0;
            } else {
                switch (opcao2) {
                    case 1:
                        System.out.println("Ola " + procura.getTitular() + " digite o valor a ser depositado");
                        double valorDep = sc.nextDouble();
                        procura.Depositar(valorDep);
                        break;
                    case 2:
                        System.out.println("Ola " + procura.getTitular() + " digite o valor a ser sacado");
                        double valorSaq = sc.nextDouble();
                        procura.Saque(valorSaq);
                        break;
                    case 3:
                        break;
                }
            }
        }while (opcao2 != 3) ;
        for (Contas f : todasContas){
            System.out.println(f);
        }
    }
}
