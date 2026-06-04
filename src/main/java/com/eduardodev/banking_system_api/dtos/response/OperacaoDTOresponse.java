package com.eduardodev.banking_system_api.dtos.response;

import java.math.BigDecimal;
import java.time.Instant;

public class OperacaoDTOresponse {
    public String titular;
    public int tipoOperacao;
    public Instant dataHora;
    public String agencia;
    public String numeroConta;
    public BigDecimal valorOperacao;
    public BigDecimal saldoApos;
}
