package com.eduardodev.banking_system_api.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"titular", "idConta", "balance", "historicoTransacoes"})
public abstract class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idConta;
    private String titular;
    protected BigDecimal balance;


    @JsonIgnore
    @OneToMany(mappedBy = "conta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transacao> historicoTransacoes = new ArrayList<>();

    public void addTransacao(Transacao transacao){
        transacao.setConta(this);
        historicoTransacoes.add(transacao);
    }

    public abstract void sacar(BigDecimal valor);
    public abstract void deposito(BigDecimal valor);
    public abstract void transferencia(BigDecimal valor, Conta contaDestino);

    @JsonIgnore
    public Transacao getUltimaTransacao() {
        if (historicoTransacoes == null || historicoTransacoes.isEmpty()) return null;
        return historicoTransacoes.getLast();
    }

    public void creditar(BigDecimal valor){
        balance = balance.add(valor);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idConta);
    }

}