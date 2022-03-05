package de.fourtytwoways.onion.domain.usecases.contract;

import de.fourtytwoways.onion.domain.entities.contract.Contract;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ContractCalculationTest {

    @Test
    void calculatePremium() {
        Contract contract = new Contract("4711", null,
                LocalDate.of(2022, 1, 1), LocalDate.of(2042, 1, 1),
                BigDecimal.valueOf(4711), null);
        assertNull(contract.getPremium());
        new ContractCalculation().calculatePremium(contract);
        assertNotNull(contract.getPremium());
        assertEquals(BigDecimal.valueOf(19.63), contract.getPremium());
    }

    @Test
    void calculateBenefit() {
        Contract contract = new Contract("4711", null,
                LocalDate.of(2022, 1, 1), LocalDate.of(2042, 1, 1),
                null, BigDecimal.valueOf(47.11));
        assertNull(contract.getBenefit());
        new ContractCalculation().calculateBenefit(contract);
        assertNotNull(contract.getBenefit());
        assertEquals(BigDecimal.valueOf(11306.4), contract.getBenefit().stripTrailingZeros());
    }
}