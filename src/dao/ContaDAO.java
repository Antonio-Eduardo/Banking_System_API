package dao;

import entities.Conta;

import java.sql.Connection;

public interface ContaDAO {
    void salvar(Conta t);
    Conta buscarPorId(Long id);
    void updateSaldo(Long id, Double saldo);
    void transferenciaOperacao(Conta origem, Conta destino);
    void updateSaldoTransferencia(Connection conn, Long id, Double saldo);
    void deleteConta(Long id);
}
