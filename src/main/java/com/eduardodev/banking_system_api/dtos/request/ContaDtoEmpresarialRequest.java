package com.eduardodev.banking_system_api.dtos.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ContaDtoEmpresarialRequest {
    private Long Id;
    private String NomeFantasia;
    private String CNPJ;
    private String RazaoSocial;
    private BigDecimal balance;
    private BigDecimal emprestimo;
}
