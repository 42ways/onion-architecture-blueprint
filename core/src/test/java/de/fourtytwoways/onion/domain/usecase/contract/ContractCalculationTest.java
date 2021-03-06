package de.fourtytwoways.onion.domain.usecase.contract;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.domain.model.contract.Contract;
import de.fourtytwoways.onion.domain.model.asset.Money;
import de.fourtytwoways.onion.domain.model.enumeration.ComputationTarget;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ContractCalculationTest {

    @Test
    void calculatePremium() {
        Contract contract = new Contract("4711", null, null,
                                         LocalDate.of(2022, 1, 1), LocalDate.of(2042, 1, 1),
                                         Money.valueOf(4711, Money.EUR), null);
        assertNull(contract.getPremium());
        new ContractCalculation().calculate(contract, ComputationTarget.PREMIUM);
        assertNotNull(contract.getPremium());
        assertEquals(Money.valueOf(19.63, Money.EUR), contract.getPremium());
    }

    @Test
    void calculateBenefit() {
        Contract contract = new Contract("4711", null, null,
                LocalDate.of(2022, 1, 1), LocalDate.of(2042, 1, 1),
                null, Money.valueOf(47.11, Money.USD));
        assertNull(contract.getBenefit());
        new ContractCalculation().calculate(contract, ComputationTarget.BENEFIT);
        assertNotNull(contract.getBenefit());
        assertEquals(Money.valueOf(11306.4, Money.USD), contract.getBenefit());
        assertEquals(Money.USD, contract.getBenefit().currency());
    }
}