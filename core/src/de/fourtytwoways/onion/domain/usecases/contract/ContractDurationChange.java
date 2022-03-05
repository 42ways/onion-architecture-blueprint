package de.fourtytwoways.onion.domain.usecases.contract;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.domain.entities.contract.Contract;

import java.time.LocalDate;

public class ContractDurationChange {
    public static Contract adjustStartDate(Contract contract, LocalDate newStartDate, boolean calculateBenefit) {
        contract.setStartDate(newStartDate);
        if (calculateBenefit)
            return ContractCalculation.calculateBenefit(contract);
        else
            return ContractCalculation.calculatePremium(contract);
    }

    // unfortunately Java doesn't have default parameters, so we have to use this noisy code repetition
    public static Contract adjustStartDate(Contract contract, LocalDate newStartDate) {
        return adjustStartDate(contract, newStartDate, true);
    }

    public static Contract adjustEndDate(Contract contract, LocalDate newEndDate, boolean calculateBenefit) {
        contract.setEndDate(newEndDate);
        if (calculateBenefit)
            return ContractCalculation.calculateBenefit(contract);
        else
            return ContractCalculation.calculatePremium(contract);
    }

    // unfortunately Java doesn't have default parameters, so we have to use this noisy code repetition
    public static Contract adjustEndDate(Contract contract, LocalDate newEndDate) {
        return adjustEndDate(contract, newEndDate, true);
    }
}
