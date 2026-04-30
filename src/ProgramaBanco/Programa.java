package ProgramaBanco;

import Banco_Contas.ContaEmpresarial;
import Banco_Contas.Contas;

public class Programa {
    public static void main(String[] args) {

        Contas acc = new Contas("Eduardo", 1001, 0.0);
        ContaEmpresarial bacc = new ContaEmpresarial("Lorena", 1002, 0.0, 500.0);

        Contas acc1 = bacc;
        Contas acc2 = new ContaEmpresarial("Bob",1004,0.0,200.0);
        ContaEmpresarial empresa1 = (ContaEmpresarial) acc2 ;

        if (bacc instanceof ContaEmpresarial empresa2) {
            empresa2.Emprestimo(200.00);
            System.out.println(empresa2.getEmprestimo());
        }
    }
}
