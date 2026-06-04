package com.eduardodev.banking_system_api.dtos.response;

import com.eduardodev.banking_system_api.dtos.request.superdtoconta.ContaDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
public class ContaCorrenteDtoResponse extends ContaDTO {
    public BigDecimal limiteChequeEspecial;


}
