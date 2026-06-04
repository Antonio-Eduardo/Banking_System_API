package com.eduardodev.banking_system_api.dtos.response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ContaCorrenteDtoResponse {
    public BigDecimal limiteChequeEspecial;

    public Long Id;
    public String titular;
    public BigDecimal balance;
    public boolean ativa;
    public LocalDate dataAbertura;
    public String agencia;
    public String numeroConta;
}
