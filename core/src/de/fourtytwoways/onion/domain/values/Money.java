package de.fourtytwoways.onion.domain.values;
// Copyright (c) 2022 Thomas Herrmann, 42ways GmbH

import lombok.NonNull;
import lombok.Value;

import java.math.BigDecimal;

@Value
public class Money {
    @NonNull BigDecimal amount;
    @NonNull Currency currency;

    public static Money valueOf(long amount, Currency currency) {
        return new Money(BigDecimal.valueOf(amount), currency);
    }

    public static Money valueOf(long amount) {
        // TODO: There has to be a way to specify default parameters without this boilerplate duplication...
        return new Money(BigDecimal.valueOf(amount), Currency.EUR);
    }

    public static Money valueOf(double amount, Currency currency) {
        return new Money(BigDecimal.valueOf(amount), currency);
    }

    public static Money valueOf(double amount) {
        // TODO: There has to be a way to specify default parameters without this boilerplate duplication...
        return new Money(BigDecimal.valueOf(amount), Currency.EUR);
    }

    public enum Currency {
        EUR,
        USD
    }

    public Money add(Money other) {
        assertSameCurrency(other);
        return new Money(amount.add(other.amount), currency);
    }

    public Money subtract(Money other) {
        assertSameCurrency(other);
        return new Money(amount.subtract(other.amount), currency);
    }

    private void assertSameCurrency(Money other) {
        if (!other.currency.equals(this.currency)) {
            throw new IllegalArgumentException("Money objects must have the same currency");
        }
    }
}