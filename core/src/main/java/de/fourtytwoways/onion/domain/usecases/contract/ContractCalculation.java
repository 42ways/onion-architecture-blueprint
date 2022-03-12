package de.fourtytwoways.onion.domain.usecases.contract;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.domain.entities.contract.Contract;
import de.fourtytwoways.onion.domain.values.Money;
import lombok.NonNull;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ContractCalculation {
    public Contract calculatePremium(@NonNull Contract contract) {
        BigDecimal durationInMonths = contract.getDurationInMonths();
        contract.setPremium(
                new Money(contract.getBenefit().getAmount().divide(durationInMonths, 2, RoundingMode.HALF_UP),
                          contract.getBenefit().getCurrency()));
        return contract;
    }

    public Contract calculateBenefit(@NonNull Contract contract) {
        BigDecimal durationInMonths = contract.getDurationInMonths();
        contract.setBenefit(
                new Money(contract.getPremium().getAmount().multiply(durationInMonths),
                          contract.getPremium().getCurrency()));
        return contract;
    }
}
