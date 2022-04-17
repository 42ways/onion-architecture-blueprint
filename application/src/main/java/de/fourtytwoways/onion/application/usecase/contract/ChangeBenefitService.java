package de.fourtytwoways.onion.application.usecase.contract;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.domain.model.contract.Contract;
import de.fourtytwoways.onion.domain.usecase.contract.ContractCalculation;
import de.fourtytwoways.onion.domain.model.asset.Money;
import de.fourtytwoways.onion.domain.model.enumeration.ComputationTarget;

public class ChangeBenefitService extends AbstractContractModificationService {
    public Contract changeBenefit(String contractNumber, Money newBenefit) {
        return modifyContract(contractNumber,
                              contract -> {
                                  contract.setBenefit(newBenefit);
                                  return new ContractCalculation().calculate(contract, ComputationTarget.PREMIUM);
                              });
    }
}
