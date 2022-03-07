package de.fourtytwoways.onion.application.usecases.contract;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.application.repositories.ContractRepository;
import de.fourtytwoways.onion.application.repositories.RepositoryRegistry;
import de.fourtytwoways.onion.domain.entities.contract.Contract;
import de.fourtytwoways.onion.domain.usecases.contract.ContractCalculation;

import java.math.BigDecimal;

public class ChangePremiumService extends AbstractContractModificationService {
    public Contract changePremium(String contractNumber, BigDecimal newPremium) {
        return modifyContract(contractNumber,
                              contract -> {
                                  contract.setPremium(newPremium);
                                  return new ContractCalculation().calculateBenefit(contract);
                              });
    }
}
