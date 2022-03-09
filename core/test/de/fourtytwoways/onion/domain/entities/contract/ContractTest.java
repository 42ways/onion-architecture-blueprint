package de.fourtytwoways.onion.domain.entities.contract;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.domain.values.Money;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

import static org.junit.jupiter.api.Assertions.*;

class ContractTest {

    @Test
    void testToString() {
        Contract contract =
                new Contract("4711", null,
                             LocalDate.of(2022, 1, 1),
                             LocalDate.of(2042, 1, 1),
                             Money.valueOf(47.11, Money.Currency.EUR),
                             Money.valueOf(08.15, Money.Currency.EUR));
        assertEquals("Contract(contractNumber=4711, product=null, startDate=2022-01-01, endDate=2042-01-01," +
                             " benefit=Money(amount=47.11, currency=EUR), premium=Money(amount=8.15, currency=EUR))",
                     contract.toString());
    }

    @Test
    void testEquals() {
        Contract contract1 = new Contract("4711", null,
                                          LocalDate.of(2022, 1, 1),
                                          LocalDate.of(2042, 1, 1),
                                          Money.valueOf(47.11, Money.Currency.EUR),
                                          Money.valueOf(08.15, Money.Currency.EUR));
        Contract contract2 = new Contract("4711", null,
                                          LocalDate.of(2022, 1, 1),
                                          LocalDate.of(2042, 1, 1),
                                          Money.valueOf(47.11),
                                          Money.valueOf(08.15));
        assert contract1.equals(contract2);
    }

    @Test
    void getDuration() {
        Contract contract = new Contract("0815", null,
                                         LocalDate.of(2022, 1, 1),
                                         LocalDate.of(2042, 1, 1),
                                         Money.valueOf(47.11), Money.valueOf(08.15));
        assertEquals(Period.of(20, 0, 0), contract.getDuration());
        contract = new Contract("0815", null,
                                LocalDate.of(2022, 1, 1),
                                LocalDate.of(2042, 4, 1),
                                Money.valueOf(47.11), Money.valueOf(08.15));
        assertEquals(Period.of(20, 3, 0), contract.getDuration());
        contract = new Contract("0815", null,
                                LocalDate.of(2022, 1, 1),
                                LocalDate.of(2041, 4, 11),
                                Money.valueOf(47.11), Money.valueOf(08.15));
        assertEquals(Period.of(19, 3, 10), contract.getDuration());
    }

    @Test
    void getDurationInMonths() {
        Contract contract = new Contract("0815", null,
                                         LocalDate.of(2022, 1, 1),
                                         LocalDate.of(2042, 1, 1),
                                         Money.valueOf(47.11), Money.valueOf(08.15));
        assertEquals(BigDecimal.valueOf(240), contract.getDurationInMonths());
        contract = new Contract("0815", null,
                                LocalDate.of(2022, 1, 1),
                                LocalDate.of(2042, 4, 1),
                                Money.valueOf(47.11), Money.valueOf(08.15));
        assertEquals(BigDecimal.valueOf(243), contract.getDurationInMonths());
        contract = new Contract("0815", null,
                                LocalDate.of(2022, 1, 1),
                                LocalDate.of(2041, 4, 11),
                                Money.valueOf(47.11), Money.valueOf(08.15));
        assertEquals(BigDecimal.valueOf(231), contract.getDurationInMonths());
    }
}