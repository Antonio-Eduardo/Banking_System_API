package ProgramaBanco;

import Banco_Contas.*;
import Excecoes.ConsoleException;
import Excecoes.NegocioException;
import Excecoes.ValidacaoException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Programa {
    public static void main(String[] args) {
        List<Contas> todasContas = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        final int quantidade = ConsoleException.lerInteiros(sc, "quantidade de cadastros: ");
        int limite = 0;
        int opcao;
        while (limite < quantidade) {
            String nome = ConsoleException.lerString(sc,"Nome titular: ");
            int numero = ConsoleException.lerInteiros(sc, "iD da conta:");
            double depositoInicial = ConsoleException.lerDouble(sc, "Realize um deposito iniciaL: ");
            opcao = ConsoleException.lerInteiros(sc, "Selecione o tipo de conta (1-Conta corrente|2-Conta empresarial|3-Conta poupanca)\n");
            switch (opcao) {
                case 1:
                    NegocioException.executar(() -> {
                                Contas accCorrente = new ContaCorrente(nome, numero, depositoInicial);
                                todasContas.add(accCorrente);
                            });
                    limite++;

                    break;
                case 2:
                    double emprestimo = ConsoleException.lerDouble(sc, "Emprestimo inicial: ");
                    NegocioException.executar(() -> {
                                Contas accEmp = new ContaEmpresarial(nome, numero, depositoInicial, emprestimo);
                                todasContas.add(accEmp);
                            });
                    limite++;
                    break;
                case 3:
                    NegocioException.executar(() -> {
                                Contas accPoup = new ContaPoupanca(nome, numero, depositoInicial);
                                todasContas.add(accPoup);
                            });
                    limite++;
                    break;
                default:
                    System.out.println("Opção inválida! tente novamente");
            }
        }
        for (Contas f : todasContas) {
            System.out.println(f);
        }
        int opcao2 = 0;

        while (opcao2 != 3 && quantidade > 0) {
            System.out.println("1-Deposito | 2-Saque | 3-sair");
            opcao2 = sc.nextInt();
            sc.nextLine();

            int numeroConta = ConsoleException.lerInteiros(sc, "Digite o numero da conta que deseja realizar o deposito: ");
            Contas procura = todasContas.stream().filter(c -> c.getNumero().equals(numeroConta)).findFirst().orElse(null);

            if (procura == null) {
                System.out.println("numero invalido");
                opcao2 = 0;
            } else {
                switch (opcao2) {
                    case 1:
                        double valorDep = ConsoleException.lerDouble(sc, "Ola " + procura.getTitular() + " digite o valor a ser depositado: ");
                        NegocioException.executar(() -> procura.Depositar(valorDep));
                        break;
                    case 2:
                        double valorSaq = ConsoleException.lerDouble(sc, "Ola " + procura.getTitular() + " digite o valor a ser sacado: ");
                        NegocioException.executar(() -> procura.Saque(valorSaq));
                        break;
                    case 3:
                        break;
                }
            }
        }
        for (Contas f : todasContas) {
            System.out.println(f);
        }
    }
}
