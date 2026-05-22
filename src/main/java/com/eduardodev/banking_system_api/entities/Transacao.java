package com.eduardodev.banking_system_api.entities;

import com.eduardodev.banking_system_api.enums.TipoOperacao;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"tipoOperacao", "valor", "saldoApos", "data", "conta"})
public class Transacao{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long iD;
    @Enumerated(EnumType.STRING)
    private TipoOperacao tipoOperacao;
    private double valor;
    private double saldoApos;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy'T'HH:mm:ss'Z'", timezone = "UTC")
    private Instant data;

    @ManyToOne
    @JoinColumn(name = "id_conta")
    private Conta conta;

    public Transacao(TipoOperacao tipoOperacao, double valor, double balance) {
        this.tipoOperacao = tipoOperacao;
        this.valor = valor;
        this.saldoApos = balance;
        this.data = Instant.now();
    }
}
