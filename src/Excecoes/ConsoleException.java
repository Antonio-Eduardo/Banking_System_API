package Excecoes;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleException {

    public static int lerInteiros(Scanner sc, String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                int valor = sc.nextInt();
                sc.nextLine();
                return valor;

            } catch (InputMismatchException e) {
                System.out.println("Numero invalido");
                sc.nextLine();
            }
        }
    }

    public static double lerDouble(Scanner sc, String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                double valorDouble = sc.nextDouble();
                sc.nextLine();
                return valorDouble;

            } catch (InputMismatchException e) {
                System.out.println("Numero invalido");
                sc.nextLine();
            }
        }
    }
    public static String lerMensagem(Scanner sc, String mensagem){
        System.out.print(mensagem);
        String mensagemEn = sc.nextLine();
        if (mensagemEn.matches(".*\\d*")){
            System.out.println("Erro: O nome nao pode conter numeros");
        } else if (mensagemEn.trim().isEmpty()) {
            System.out.println("Erro: O nome nao pode estar vazio");
        }
        return mensagemEn;
    }
}




