package com.eduardodev.banking_system_api.dtos.request.superdtoconta;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ContaDtoRequest {
    public String Titular;
    public BigDecimal balance;
    public boolean ativa;
    public LocalDate dataAbertura;
    public String agencia;
    public String numeroConta;
}
