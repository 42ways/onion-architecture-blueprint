package de.fourtytwoways.onion.application.usecase.contract;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.domain.model.contract.Contract;
import de.fourtytwoways.onion.domain.usecase.contract.ContractCalculation;
import de.fourtytwoways.onion.domain.model.asset.Money;
import de.fourtytwoways.onion.domain.model.enumeration.ComputationTarget;

public class ChangePremiumService extends AbstractContractModificationService {
    public Contract changePremium(String contractNumber, Money newPremium) {
        return modifyContract(contractNumber,
                              contract -> {
                                  contract.setPremium(newPremium);
                                  return new ContractCalculation().calculate(contract, ComputationTarget.BENEFIT);
                              });
    }
}
