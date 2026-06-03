package com.eduardodev.banking_system_api.dtos.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ContaDTOresponse {
    public Long id;
    public String Titular;
    public BigDecimal balance;
}
