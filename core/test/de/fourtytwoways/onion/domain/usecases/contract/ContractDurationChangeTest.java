package de.fourtytwoways.onion.domain.usecases.contract;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.domain.entities.contract.Contract;
import de.fourtytwoways.onion.domain.values.Money;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ContractDurationChangeTest {

    @Test
    void adjustStartDate() {
        Contract contract = new Contract("4711", null,
                                         LocalDate.of(2022, 1, 1), LocalDate.of(2042, 1, 1),
                                         Money.valueOf(4711, Money.Currency.EUR), null);
        new ContractCalculation().calculatePremium(contract);
        assertEquals(BigDecimal.valueOf(19.63), contract.getPremium().getAmount());
        assertEquals(BigDecimal.valueOf(4711), contract.getBenefit().getAmount());
        new ContractDurationChange().adjustStartDate(contract, LocalDate.of(2032, 1, 1), false);
        assertEquals(BigDecimal.valueOf(39.26), contract.getPremium().getAmount());
        assertEquals(Money.Currency.EUR, contract.getPremium().getCurrency());
        assertEquals(BigDecimal.valueOf(4711), contract.getBenefit().getAmount());
        assertEquals(Money.Currency.EUR, contract.getBenefit().getCurrency());
        new ContractDurationChange().adjustStartDate(contract, LocalDate.of(2022, 1, 1));
        assertEquals(BigDecimal.valueOf(39.26), contract.getPremium().getAmount());
        assertEquals(BigDecimal.valueOf(9422.40), contract.getBenefit().getAmount().stripTrailingZeros());
    }

    @Test
    void adjustEndDate() {
        Contract contract = new Contract("4711", null,
                                         LocalDate.of(2022, 1, 1), LocalDate.of(2042, 1, 1),
                                         Money.valueOf(4711, Money.Currency.USD), null);
        new ContractCalculation().calculatePremium(contract);
        assertEquals(Money.valueOf(19.63, Money.Currency.USD), contract.getPremium());
        assertEquals(Money.valueOf(4711, Money.Currency.USD), contract.getBenefit());
        new ContractDurationChange().adjustEndDate(contract, LocalDate.of(2032, 1, 1));
        assertEquals(Money.valueOf(19.63, Money.Currency.USD), contract.getPremium());
        // TODO: compare BigDecimal without the trailing zeros trouble...
        assertEquals(BigDecimal.valueOf(2355.60), contract.getBenefit().getAmount().stripTrailingZeros());
        new ContractDurationChange().adjustEndDate(contract, LocalDate.of(2042, 1, 1),false);
        assertEquals(BigDecimal.valueOf(9.82), contract.getPremium().getAmount());
        assertEquals(BigDecimal.valueOf(2355.60), contract.getBenefit().getAmount().stripTrailingZeros());
    }
}