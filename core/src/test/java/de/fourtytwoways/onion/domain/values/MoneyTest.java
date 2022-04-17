package de.fourtytwoways.onion.domain.values;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.domain.model.asset.Money;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;

import static org.junit.jupiter.api.Assertions.*;

class MoneyTest {

    @Test
    void valueOf() {
        Money m1 = Money.valueOf(1.0);
        Currency eur = Currency.getInstance("EUR");
        assertEquals(Money.valueOf(BigDecimal.valueOf(1.), eur), m1);
        assertEquals(Money.valueOf(1., eur), m1);
        assertEquals(Money.valueOf(1, eur), m1);

        Money m2 = Money.valueOf(1.0, Currency.getInstance("USD"));
        assertEquals(Money.valueOf(1, Currency.getInstance("USD")), m2);
        assertNotEquals(m1, m2);
        assertNotEquals(Money.valueOf(BigDecimal.valueOf(1.), eur), m2);
        assertNotEquals(Money.valueOf(1., eur), m2);
        assertNotEquals(Money.valueOf(1, eur), m2);

        Money m3 = Money.valueOf(1.2345);
        assertEquals(BigDecimal.valueOf(1.23), m3.amount());
        Money m4 = Money.valueOf(1234.5678);
        assertEquals(BigDecimal.valueOf(1234.57), m4.amount());
    }

    @Test
    void add() {
        Money m1 = Money.valueOf(1.2);
        Money m2 = Money.valueOf(3.4);
        assertEquals(Money.valueOf(4.6), m1.add(m2));
        Money m3 = Money.valueOf(3.4, Currency.getInstance("USD"));
        assertThrows(IllegalArgumentException.class, () -> m1.add(m3));
    }

    @Test
    void subtract() {
        Money m1 = Money.valueOf(1.2);
        Money m2 = Money.valueOf(3.4);
        assertEquals(Money.valueOf(-2.2), m1.subtract(m2));
        Money m3 = Money.valueOf(3.4, Currency.getInstance("USD"));
        assertThrows(IllegalArgumentException.class, () -> m1.subtract(m3));
    }

    @Test
    void multiply() {
        Money m1 = Money.valueOf(1.2);
        Money m2 = Money.valueOf(3.4);
        assertEquals(Money.valueOf(4.08), m1.multiply(m2));
        Money m3 = Money.valueOf(3.4, Currency.getInstance("USD"));
        assertThrows(IllegalArgumentException.class, () -> m1.multiply(m3));
    }

    @Test
    void divide() {
        Money m1 = Money.valueOf(1.2);
        Money m2 = Money.valueOf(3.4);
        assertEquals(Money.valueOf(0.35), m1.divide(m2));
        Money m3 = Money.valueOf(3.4, Currency.getInstance("USD"));
        assertThrows(IllegalArgumentException.class, () -> m1.divide(m3));
    }

    @Test
    void amount() {
        Money m1 = Money.valueOf(1.2);
        assertEquals(BigDecimal.valueOf(1.2).setScale(2, RoundingMode.HALF_UP), m1.amount());
        assertEquals("Money[amount=1.20, currency=EUR]", m1.toString());

        Money m2 = Money.valueOf(12345.678, Money.JPY);
        assertEquals(BigDecimal.valueOf(12346), m2.amount());
        assertEquals("Money[amount=12346, currency=JPY]", m2.toString());

        Money m3 = Money.valueOf(12345.6789, Money.USD);
        assertEquals(BigDecimal.valueOf(12345.68), m3.amount());
        assertEquals("Money[amount=12345.68, currency=USD]", m3.toString());
    }

    @Test
    void currency() {
        Money m1 = Money.valueOf(1.2);
        assertEquals(Currency.getInstance("EUR"), m1.currency());
        Money m2 = Money.valueOf(1.2, Currency.getInstance("USD"));
        assertEquals(Currency.getInstance("USD"), m2.currency());
    }

}