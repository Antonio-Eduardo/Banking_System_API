package com.eduardodev.banking_system_api.entities;

import com.eduardodev.banking_system_api.enums.TipoOperacao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Data
@NoArgsConstructor
public class Transacao{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long iD;
    @Enumerated(EnumType.STRING)
    private TipoOperacao tipoOperacao;
    private double valor;
    private double saldoApos;
    private LocalDateTime data;

    @ManyToOne
    @JoinColumn(name = "id_conta")
    private Conta conta;

    public Transacao(TipoOperacao tipoOperacao, double valor, double balance) {
        this.tipoOperacao = tipoOperacao;
        this.valor = valor;
        this.saldoApos = balance;
        this.data = LocalDateTime.now();
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
