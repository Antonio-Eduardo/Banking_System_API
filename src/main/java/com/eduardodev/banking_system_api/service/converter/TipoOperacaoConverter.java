package com.eduardodev.banking_system_api.service.converter;

import com.eduardodev.banking_system_api.enums.TipoOperacao;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class TipoOperacaoConverter implements AttributeConverter<TipoOperacao, Integer> {
    @Override
    public Integer convertToDatabaseColumn(TipoOperacao tipoOperacao) {
        if (tipoOperacao == null) {
            return null;
        }
        return tipoOperacao.getValue();
    }

    @Override
    public TipoOperacao convertToEntityAttribute(Integer integer) {
        if (integer == null) {
            return null;
        }
        return TipoOperacao.fromValue(integer);
    }

}
