package de.fourtytwoways.onion.domain.values;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.*;

class MoneyTest {

    @Test
    void valueOf() {
        Money m1 = Money.valueOf(1.0);
        assertEquals(Money.valueOf(BigDecimal.valueOf(1.), Money.Currency.EUR), m1);
        assertEquals(Money.valueOf(1., Money.Currency.EUR), m1);
        assertEquals(Money.valueOf(1, Money.Currency.EUR), m1);

        Money m2 = Money.valueOf(1.0, Money.Currency.USD);
        assertEquals(Money.valueOf(1, Money.Currency.USD), m2);
        assertNotEquals(m1, m2);
        assertNotEquals(Money.valueOf(BigDecimal.valueOf(1.), Money.Currency.EUR), m2);
        assertNotEquals(Money.valueOf(1., Money.Currency.EUR), m2);
        assertNotEquals(Money.valueOf(1, Money.Currency.EUR), m2);

        Money m3 = Money.valueOf(1.2345);
        assertEquals(BigDecimal.valueOf(1.23), m3.getAmount());
        Money m4 = Money.valueOf(1234.5678);
        assertEquals(BigDecimal.valueOf(1234.57), m4.getAmount());
    }

    @Test
    void add() {
        Money m1 = Money.valueOf(1.2);
        Money m2 = Money.valueOf(3.4);
        assertEquals(Money.valueOf(4.6), m1.add(m2));
        Money m3 = Money.valueOf(3.4, Money.Currency.USD);
        assertThrows(IllegalArgumentException.class, () -> m1.add(m3));
    }

    @Test
    void subtract() {
        Money m1 = Money.valueOf(1.2);
        Money m2 = Money.valueOf(3.4);
        assertEquals(Money.valueOf(-2.2), m1.subtract(m2));
        Money m3 = Money.valueOf(3.4, Money.Currency.USD);
        assertThrows(IllegalArgumentException.class, () -> m1.subtract(m3));
    }

    @Test
    void multiply() {
        Money m1 = Money.valueOf(1.2);
        Money m2 = Money.valueOf(3.4);
        assertEquals(Money.valueOf(4.08), m1.multiply(m2));
        Money m3 = Money.valueOf(3.4, Money.Currency.USD);
        assertThrows(IllegalArgumentException.class, () -> m1.multiply(m3));
    }

    @Test
    void divide() {
        Money m1 = Money.valueOf(1.2);
        Money m2 = Money.valueOf(3.4);
        assertEquals(Money.valueOf(0.35), m1.divide(m2));
        Money m3 = Money.valueOf(3.4, Money.Currency.USD);
        assertThrows(IllegalArgumentException.class, () -> m1.divide(m3));
    }

    @Test
    void getAmount() {
        Money m1 = Money.valueOf(1.2);
        assertEquals(BigDecimal.valueOf(1.2).setScale(2, RoundingMode.HALF_UP), m1.getAmount());
    }

    @Test
    void getCurrency() {
        Money m1 = Money.valueOf(1.2);
        assertEquals(Money.Currency.EUR, m1.getCurrency());
        Money m2 = Money.valueOf(1.2, Money.Currency.USD);
        assertEquals(Money.Currency.USD, m2.getCurrency());
    }

}