package de.fourtytwoways.onion.domain.values;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import lombok.NonNull;
import lombok.Value;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Value
public class Money {
    private final static int SCALE = 2;

    @NonNull BigDecimal amount;
    @NonNull Currency currency;

    public static Money valueOf(BigDecimal amount, Currency currency) {
        return new Money(amount.setScale(SCALE, RoundingMode.HALF_UP), currency);
    }

    public static Money valueOf(long amount, Currency currency) {
        return valueOf(BigDecimal.valueOf(amount), currency);
    }

    public static Money valueOf(long amount) {
        // TODO: There has to be a way to specify default parameters without this boilerplate duplication...
        return valueOf(amount, Currency.EUR);
    }

    public static Money valueOf(double amount, Currency currency) {
        return valueOf(BigDecimal.valueOf(amount), currency);
    }

    public static Money valueOf(double amount) {
        // TODO: There has to be a way to specify default parameters without this boilerplate duplication...
        return valueOf(amount, Currency.EUR);
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

    public Money multiply(Money other) {
        assertSameCurrency(other);
        return Money.valueOf(amount.multiply(other.amount), currency);
    }

    public Money divide(Money other) {
        assertSameCurrency(other);
        return new Money(amount.divide(other.amount, SCALE, RoundingMode.HALF_UP), currency);
    }

    private void assertSameCurrency(Money other) {
        if (!other.currency.equals(this.currency)) {
            throw new IllegalArgumentException("Money objects must have the same currency");
        }
    }
}