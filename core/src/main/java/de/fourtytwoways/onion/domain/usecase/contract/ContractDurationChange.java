package de.fourtytwoways.onion.domain.usecase.contract;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.domain.model.contract.Contract;
import de.fourtytwoways.onion.domain.model.enumeration.ComputationTarget;

import java.time.LocalDate;

public class ContractDurationChange {
    public Contract adjustStartDate(Contract contract, LocalDate newStartDate, ComputationTarget computationTarget) {
        // TODO: Wouldn't it be good to have contract immutable?
        contract.setStartDate(newStartDate);
        return new ContractCalculation().calculate(contract, computationTarget);
    }

    // unfortunately Java doesn't have default parameters, so we have to use this noisy code repetition
    public Contract adjustStartDate(Contract contract, LocalDate newStartDate) {
        return adjustStartDate(contract, newStartDate, ComputationTarget.BENEFIT);
    }

    public Contract adjustEndDate(Contract contract, LocalDate newEndDate, ComputationTarget computationTarget) {
        contract.setEndDate(newEndDate);
        return new ContractCalculation().calculate(contract, computationTarget);
    }

    // unfortunately Java doesn't have default parameters, so we have to use this noisy code repetition
    public Contract adjustEndDate(Contract contract, LocalDate newEndDate) {
        return adjustEndDate(contract, newEndDate, ComputationTarget.BENEFIT);
    }
}
