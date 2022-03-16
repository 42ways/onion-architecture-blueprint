package de.fourtytwoways.onion.domain.values;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;

// To keep it simple we use our own little Money class, built on java.math.BigDecimal and java.util.Currency
// As an alternative we could use an implementation of JSR 354 (https://jcp.org/en/jsr/detail?id=354),
// e.g. JavaMoney (https://javamoney.github.io/)
public record Money (BigDecimal amount, Currency currency){
    public static final Currency EUR = Currency.getInstance("EUR");
    public static final Currency USD = Currency.getInstance("USD");
    public static final Currency JPY = Currency.getInstance("JPY");
    public static final Currency defaultCurrency = EUR;

    public static Money valueOf(BigDecimal amount, Currency currency) {
        return new Money(amount.setScale(currency.getDefaultFractionDigits(), RoundingMode.HALF_UP), currency);
    }

    public static Money valueOf(long amount, Currency currency) {
        return valueOf(BigDecimal.valueOf(amount), currency);
    }

    public static Money valueOf(long amount) {
        // TODO: There has to be a way to specify default parameters without this boilerplate duplication...
        return valueOf(amount, defaultCurrency);
    }

    public static Money valueOf(double amount, Currency currency) {
        return valueOf(BigDecimal.valueOf(amount), currency);
    }

    public static Money valueOf(double amount) {
        // TODO: There has to be a way to specify default parameters without this boilerplate duplication...
        return valueOf(amount, defaultCurrency);
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
        return new Money(amount.divide(other.amount, currency.getDefaultFractionDigits(), RoundingMode.HALF_UP), currency);
    }

    private void assertSameCurrency(Money other) {
        if (!other.currency.equals(this.currency)) {
            throw new IllegalArgumentException("Money objects must have the same currency");
        }
    }
}