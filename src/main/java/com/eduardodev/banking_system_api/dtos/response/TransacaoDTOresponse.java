package com.eduardodev.banking_system_api.dtos.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransacaoDTOresponse {
    public Long id;
    public BigDecimal valor;
    public BigDecimal saldoApos;
}
