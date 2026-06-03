package com.eduardodev.banking_system_api.dtos.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ContaDtoCorrenteRequest {
    public String Titular;
    public BigDecimal balance;
}
