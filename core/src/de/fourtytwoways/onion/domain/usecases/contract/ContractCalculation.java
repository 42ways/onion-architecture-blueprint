package de.fourtytwoways.onion.domain.usecases.contract;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.domain.entities.contract.Contract;
import lombok.NonNull;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Period;

public class ContractCalculation {
    public Contract calculatePremium(@NonNull Contract contract) {
        BigDecimal durationInMonths = contract.getDurationInMonths();
        contract.setPremium(contract.getBenefit().divide(durationInMonths, 2, RoundingMode.HALF_UP));
        return contract;
    }

    public Contract calculateBenefit(@NonNull Contract contract) {
        BigDecimal durationInMonths = contract.getDurationInMonths();
        contract.setBenefit(contract.getPremium().multiply(durationInMonths));
        return contract;
    }
}
