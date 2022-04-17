package de.fourtytwoways.onion.domain.model.contract;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.domain.model.asset.Money;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ContractTest {

    @Test
    void testToString() {
        Contract contract =
                new Contract("4711", null, null,
                             LocalDate.of(2022, 1, 1),
                             LocalDate.of(2042, 1, 1),
                             Money.valueOf(47.11, Money.EUR),
                             Money.valueOf(08.15, Money.EUR));
        assertEquals("Contract(contractNumber=4711, product=null, beneficiary=null, startDate=2022-01-01, endDate=2042-01-01," +
                             " benefit=Money[amount=47.11, currency=EUR], premium=Money[amount=8.15, currency=EUR])",
                     contract.toString());
    }

    @Test
    void testEquals() {
        Contract contract1 = new Contract("4711", null, null,
                                          LocalDate.of(2022, 1, 1),
                                          LocalDate.of(2042, 1, 1),
                                          Money.valueOf(47.11, Money.EUR),
                                          Money.valueOf(08.15, Money.EUR));
        Contract contract2 = new Contract("4711", null, null,
                                          LocalDate.of(2022, 1, 1),
                                          LocalDate.of(2042, 1, 1),
                                          Money.valueOf(47.11),
                                          Money.valueOf(08.15));
        assert contract1.equals(contract2);
    }

    @ParameterizedTest
    @CsvSource({
            "2022, 1, 1, 2042, 1,  1, 20, 0, 0",
            "2022, 1, 1, 2042, 4,  1, 20, 3, 0",
            "2022, 1, 1, 2041, 4, 11, 19, 3, 10",
            "2022, 1, 1, 2022, 1,  1,  0, 0, 0"
    })
    void getDuration(int startYear, int startMonth, int startDay,
                     int endYear, int endMonth, int endDay,
                     int expectedYears, int expectedMonths, int expectedDays) {
        Contract contract = new Contract("0815", null, null,
                                         LocalDate.of(startYear, startMonth, startDay),
                                         LocalDate.of(endYear, endMonth, endDay),
                                         Money.valueOf(47.11), Money.valueOf(08.15));

        Period duration = contract.getDuration();

        assertEquals(Period.of(expectedYears, expectedMonths, expectedDays), duration);
    }

    @ParameterizedTest
    @CsvSource({
            "2022, 1, 1, 2042, 1,  1, 240",
            "2022, 1, 1, 2042, 4,  1, 243",
            "2022, 1, 1, 2041, 4, 11, 231",
            "2022, 1, 1, 2022, 1,  1,   0"
    })
    void getDurationInMonths(int startYear, int startMonth, int startDay,
                             int endYear, int endMonth, int endDay,
                             int expectedDurationInMonths) {
        Contract contract = new Contract("0815", null, null,
                                         LocalDate.of(startYear, startMonth, startDay),
                                         LocalDate.of(endYear, endMonth, endDay),
                                         Money.valueOf(4711), Money.valueOf(08.15));

        BigDecimal durationInMonths = contract.getDurationInMonths();

        assertEquals(BigDecimal.valueOf(expectedDurationInMonths), durationInMonths);
    }
}