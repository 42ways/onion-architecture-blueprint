package de.fourtytwoways.onion.application.usecases.contract;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.application.repositories.ContractRepository;
import de.fourtytwoways.onion.application.repositories.RepositoryRegistry;
import de.fourtytwoways.onion.domain.entities.contract.Contract;
import de.fourtytwoways.onion.domain.usecases.contract.ContractCalculation;
import de.fourtytwoways.onion.domain.usecases.contract.ContractDurationChange;

import java.math.BigDecimal;

public class ChangeBenefitService extends AbstractContractModificationService {
    public Contract changeBenefit(String contractNumber, BigDecimal newBenefit) {
        return modifyContract(contractNumber,
                              contract -> {
                                  contract.setBenefit(newBenefit);
                                  return new ContractCalculation().calculatePremium(contract);
                              });
    }
}
