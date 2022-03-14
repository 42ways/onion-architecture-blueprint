package de.fourtytwoways.onion.application.usecases.contract;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.domain.entities.contract.Contract;
import de.fourtytwoways.onion.domain.usecases.contract.ContractCalculation;
import de.fourtytwoways.onion.domain.values.Money;
import de.fourtytwoways.onion.domain.values.enumeration.ComputationTarget;

public class ChangePremiumService extends AbstractContractModificationService {
    public Contract changePremium(String contractNumber, Money newPremium) {
        return modifyContract(contractNumber,
                              contract -> {
                                  contract.setPremium(newPremium);
                                  return new ContractCalculation().calculate(contract, ComputationTarget.BENEFIT);
                              });
    }
}
