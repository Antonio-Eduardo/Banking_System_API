package Banco_Contas;

import ENUM.TipoOperacao;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transacao {
    private TipoOperacao tipoOperacao;
    private double valor;
    private double saldoApos;
    private LocalDateTime data;

    public Transacao(TipoOperacao tipoOperacao, double valor, double saldoApos) {
        this.tipoOperacao = tipoOperacao;
        this.valor = valor;
        this.saldoApos = saldoApos;
        this.data = LocalDateTime.now();
    }

    public LocalDateTime getData() {
        return data;
    }

    public double getSaldoApos() {
        return saldoApos;
    }

    public TipoOperacao getTipoOperacao() {
        return tipoOperacao;
    }

    public double getValor() {
        return valor;
    }

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        final StringBuilder sb = new StringBuilder(" |tipoOperacao= ").append(tipoOperacao);
        sb.append(" |valor= ").append(valor);
        sb.append(" |saldoApos= ").append(saldoApos);
        sb.append(" |data= ").append(data.format(fmt));
        return sb.toString();
    }
}
