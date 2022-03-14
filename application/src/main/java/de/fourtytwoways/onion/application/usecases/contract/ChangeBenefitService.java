package de.fourtytwoways.onion.application.usecases.contract;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.domain.entities.contract.Contract;
import de.fourtytwoways.onion.domain.usecases.contract.ContractCalculation;
import de.fourtytwoways.onion.domain.values.Money;
import de.fourtytwoways.onion.domain.values.enumeration.ComputationTarget;

public class ChangeBenefitService extends AbstractContractModificationService {
    public Contract changeBenefit(String contractNumber, Money newBenefit) {
        return modifyContract(contractNumber,
                              contract -> {
                                  contract.setBenefit(newBenefit);
                                  return new ContractCalculation().calculate(contract, ComputationTarget.PREMIUM);
                              });
    }
}
