package com.casino.balance.domain;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.math.BigDecimal;

@Converter(autoApply = true)
class BalanceValueConverter implements AttributeConverter<BalanceValue, BigDecimal> {

    @Override
    public BigDecimal convertToDatabaseColumn(BalanceValue attribute) {
        return attribute.getValueAsBigDecimal();
    }

    @Override
    public BalanceValue convertToEntityAttribute(BigDecimal dbData) {
        return new BalanceValue(dbData);
    }
}
