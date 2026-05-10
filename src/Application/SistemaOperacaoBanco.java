package Application;

import Repository.HistoricoTransacaoTxtRepositorio;
import Banco_Contas.*;
import Services.HistoricoTransacaoTxT;

public class SistemaOperacaoBanco {
    private final HistoricoTransacaoTxT repo = new HistoricoTransacaoTxtRepositorio();
    public void processDeposito(Contas conta, double valor, String id) {
        conta.deposito(valor, id);
        Transacao t = conta.getUltimaTransacao();
        if (t != null) {
            repo.salvar(t);
        }
    }

    public void processSaque(Contas conta, double valor, String id) {
        conta.sacar(valor, id);
        Transacao t = conta.getUltimaTransacao();
        if (t != null) {
            repo.salvar(t);
        }
    }
}
