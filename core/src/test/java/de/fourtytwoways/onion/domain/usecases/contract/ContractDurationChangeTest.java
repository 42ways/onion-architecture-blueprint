package de.fourtytwoways.onion.domain.usecases.contract;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.domain.entities.contract.Contract;
import de.fourtytwoways.onion.domain.values.Money;
import de.fourtytwoways.onion.domain.values.enumeration.ComputationTarget;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ContractDurationChangeTest {

    @Test
    void adjustStartDate() {
        Contract contract = new Contract("4711", null, null,
                                         LocalDate.of(2022, 1, 1), LocalDate.of(2042, 1, 1),
                                         Money.valueOf(4711, Money.EUR), null);
        new ContractCalculation().calculate(contract, ComputationTarget.PREMIUM);
        assertEquals(Money.valueOf(19.63), contract.getPremium());
        assertEquals(Money.valueOf(4711.00), contract.getBenefit());
        new ContractDurationChange().adjustStartDate(contract, LocalDate.of(2032, 1, 1), ComputationTarget.PREMIUM);
        assertEquals(Money.valueOf(39.26), contract.getPremium());
        assertEquals(Money.EUR, contract.getPremium().currency());
        assertEquals(Money.valueOf(4711), contract.getBenefit());
        assertEquals(Money.EUR, contract.getBenefit().currency());
        new ContractDurationChange().adjustStartDate(contract, LocalDate.of(2022, 1, 1));
        assertEquals(Money.valueOf(39.26), contract.getPremium());
        assertEquals(Money.valueOf(9422.40), contract.getBenefit());
    }

    @Test
    void adjustEndDate() {
        Contract contract = new Contract("4711", null, null,
                                         LocalDate.of(2022, 1, 1), LocalDate.of(2042, 1, 1),
                                         Money.valueOf(4711, Money.USD), null);
        new ContractCalculation().calculate(contract, ComputationTarget.PREMIUM);
        assertEquals(Money.valueOf(19.63, Money.USD), contract.getPremium());
        assertEquals(Money.valueOf(4711, Money.USD), contract.getBenefit());
        new ContractDurationChange().adjustEndDate(contract, LocalDate.of(2032, 1, 1));
        assertEquals(Money.valueOf(19.63, Money.USD), contract.getPremium());
        assertEquals(Money.valueOf(2355.60, Money.USD), contract.getBenefit());
        new ContractDurationChange().adjustEndDate(contract, LocalDate.of(2042, 1, 1),ComputationTarget.PREMIUM);
        assertEquals(Money.valueOf(9.82, Money.USD), contract.getPremium());
        assertEquals(Money.valueOf(2355.60, Money.USD), contract.getBenefit());
    }
}