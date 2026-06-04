package com.eduardodev.banking_system_api.dtos.response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ContaDtoEmpresarialResponse {
    public Long id;
    public String cnpj;
    public String razaoSocial;
    public BigDecimal emprestimo;

    public String titular;
    public BigDecimal balance;
    public boolean ativa;
    public LocalDate dataAbertura;
    public String agencia;
    public String numeroConta;
}
