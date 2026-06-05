package com.eduardodev.banking_system_api.dtos.request;

import com.eduardodev.banking_system_api.dtos.request.superdtoconta.ContaDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
public class ContaDtoPoupancaRequest extends ContaDTO {
    public LocalDate dataAniversario;
}
