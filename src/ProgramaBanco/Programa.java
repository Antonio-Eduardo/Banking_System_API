package ProgramaBanco;

import BancoServicos.HistoricoTransacaoTxtRepositorio;
import BancoServicos.SistemaOperacaoBanco;
import Banco_Contas.*;
import ENUM.TipoOperacao;
import Excecoes.ConsoleException;
import Excecoes.NegocioException;
import Interfaces.HistoricoTransacaoTxT;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Programa {
    public static void main(String[] args) {
        SistemaOperacaoBanco service = new SistemaOperacaoBanco();
        HistoricoTransacaoTxT repo = new HistoricoTransacaoTxtRepositorio();

        List<Contas> todasContas = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        final int quantidade = ConsoleException.lerInteiros(sc, "quantidade de cadastros: ");
        int limite = 0;
        int opcao;
        while (limite < quantidade) {
            String nome = ConsoleException.lerString(sc, "Nome titular: ");
            System.out.print("iD da conta: ");
            String idConta = sc.nextLine();
            double depositoInicial = ConsoleException.lerDouble(sc, "Realize um deposito iniciaL: ");
            opcao = ConsoleException.lerInteiros(sc, "Selecione o tipo de conta (1-Conta corrente|2-Conta empresarial|3-Conta poupanca)\n");
            switch (opcao) {
                case 1:
                    NegocioException.executar(() -> {
                        Contas accCorrente = new ContaCorrente(nome, idConta, depositoInicial);
                        todasContas.add(accCorrente);
                    });
                    limite++;
                    break;
                case 2:
                    double emprestimo = ConsoleException.lerDouble(sc, "Emprestimo inicial: ");
                    NegocioException.executar(() -> {
                        Contas accEmp = new ContaEmpresarial(nome, idConta, depositoInicial, emprestimo);
                        todasContas.add(accEmp);
                    });
                    limite++;
                    break;
                case 3:
                    NegocioException.executar(() -> {
                        Contas accPoup = new ContaPoupanca(nome, idConta, depositoInicial);
                        todasContas.add(accPoup);
                    });
                    limite++;
                    break;
                default:
                    System.out.println("Opção inválida! tente novamente");
            }
        }
        for (Contas f : todasContas) {
            System.out.println(f.getTitular()+ "|" + f.getIdConta()+ "|"+f.getBalance());
        }
        while (true) {
            int op2 = ConsoleException.lerInteiros(sc, "1-DEPOSITAR |2-SACAR |3-EXTRATO |4-SAIR\n");
            if (op2 == 4) {
                break;
            }
            if (op2 == 3) {
                System.out.println("Digite o ID da conta: ");
                String idBusca = sc.nextLine();

                List<Transacao> transacoes = repo.listarPorConta(idBusca);

                for (Transacao t : transacoes) {
                    System.out.println(t);
                }
            }
            if (op2 == 1 || op2 == 2) {
                System.out.println("Digite o numero da conta que deseja realizar a operacao: ");
                String numeroConta = sc.nextLine();
                Contas procura = todasContas.stream().filter(c -> c.getIdConta().equals(numeroConta)).findFirst().orElse(null);
                if (procura == null) {
                    System.out.println("numero invalido");
                    continue;
                }
                System.out.println("Ola " + procura.getTitular() + " digite o valor: ");
                double valor = sc.nextDouble();
                if (op2 == 1) {
                    String idContaT = procura.getIdConta();
                    NegocioException.executar(() -> service.processDeposito(procura, valor, idContaT));
                } else {
                    String idContaT = procura.getIdConta();
                    NegocioException.executar(() -> service.processSaque(procura, valor, idContaT));
                }
            }
        }
        String path = "C:\\temp\\bancoTeste.txt";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            Collections.sort(todasContas);
            for (Contas f : todasContas) {
                bw.write(String.valueOf(f));
                bw.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (Contas f : todasContas) {
            System.out.println(f);
        }
    }
}
