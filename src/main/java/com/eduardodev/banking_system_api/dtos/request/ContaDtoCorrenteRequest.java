package com.eduardodev.banking_system_api.dtos.request;

import com.eduardodev.banking_system_api.dtos.request.superdtoconta.ContaDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
public class ContaDtoCorrenteRequest extends ContaDTO {

    public BigDecimal limiteChequeEspecial;


}
