package de.fourtytwoways.onion.domain.usecases.contract;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.domain.entities.contract.Contract;
import de.fourtytwoways.onion.domain.values.Money;
import de.fourtytwoways.onion.domain.values.enumeration.ComputationTarget;
import lombok.NonNull;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ContractCalculation {
    public Contract calculate(@NonNull Contract contract, ComputationTarget computationTarget) {
        switch (computationTarget) {
            case BENEFIT -> {return new ContractCalculation().calculateBenefit(contract);}
            case PREMIUM -> {return new ContractCalculation().calculatePremium(contract);}
            default -> throw new IllegalArgumentException("Invalid computation target");
        }
    }

    public Contract calculatePremium(@NonNull Contract contract) {
        BigDecimal durationInMonths = contract.getDurationInMonths();
        contract.setPremium(
                new Money(contract.getBenefit().amount().divide(durationInMonths, 2, RoundingMode.HALF_UP),
                          contract.getBenefit().currency()));
        return contract;
    }

    public Contract calculateBenefit(@NonNull Contract contract) {
        BigDecimal durationInMonths = contract.getDurationInMonths();
        contract.setBenefit(
                new Money(contract.getPremium().amount().multiply(durationInMonths),
                          contract.getPremium().currency()));
        return contract;
    }
}
