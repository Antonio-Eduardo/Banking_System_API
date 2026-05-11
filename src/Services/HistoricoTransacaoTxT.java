package Services;

import Entities.Transacao;
import java.util.List;

public interface HistoricoTransacaoTxT {
    void salvar(Transacao t);
    List<Transacao>listarPorConta(String iD);
}
