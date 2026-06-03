package com.eduardodev.banking_system_api.dtos.request;

import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
public class TransacaoDTOrequest {

    public BigDecimal valor;
    public BigDecimal saldoApos;
}
