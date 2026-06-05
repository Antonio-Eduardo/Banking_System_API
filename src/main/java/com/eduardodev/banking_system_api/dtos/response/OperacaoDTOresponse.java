package com.eduardodev.banking_system_api.dtos.response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
public class OperacaoDTOresponse {
    public Long id;
    public String titular;
    public Integer tipoOperacao;
    public Instant dataHora;
    public String agencia;
    public String numeroConta;
    public BigDecimal valorOperacao;
    public BigDecimal saldoApos;
}
