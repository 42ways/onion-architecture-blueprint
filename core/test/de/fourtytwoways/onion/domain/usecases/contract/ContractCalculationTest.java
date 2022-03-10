package de.fourtytwoways.onion.domain.usecases.contract;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.domain.entities.contract.Contract;
import de.fourtytwoways.onion.domain.values.Money;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ContractCalculationTest {

    @Test
    void calculatePremium() {
        Contract contract = new Contract("4711", null,
                                         LocalDate.of(2022, 1, 1), LocalDate.of(2042, 1, 1),
                                         Money.valueOf(4711, Money.Currency.EUR), null);
        assertNull(contract.getPremium());
        new ContractCalculation().calculatePremium(contract);
        assertNotNull(contract.getPremium());
        assertEquals(Money.valueOf(19.63, Money.Currency.EUR), contract.getPremium());
    }

    @Test
    void calculateBenefit() {
        Contract contract = new Contract("4711", null,
                LocalDate.of(2022, 1, 1), LocalDate.of(2042, 1, 1),
                null, Money.valueOf(47.11, Money.Currency.USD));
        assertNull(contract.getBenefit());
        new ContractCalculation().calculateBenefit(contract);
        assertNotNull(contract.getBenefit());
        assertEquals(Money.valueOf(11306.4, Money.Currency.USD), contract.getBenefit());
        assertEquals(Money.Currency.USD, contract.getBenefit().getCurrency());
    }
}