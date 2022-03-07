package de.fourtytwoways.onion.application.usecases.contract;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.application.repositories.ContractRepository;
import de.fourtytwoways.onion.application.repositories.RepositoryRegistry;
import de.fourtytwoways.onion.domain.entities.contract.Contract;
import de.fourtytwoways.onion.domain.usecases.contract.ContractCalculation;

import java.math.BigDecimal;

public class ChangeBenefitService {
    public Contract changeBenefit(String contractNumber, BigDecimal newBenefit) {
        ContractRepository contractRepository = (ContractRepository) RepositoryRegistry.getInstance().getRepository(ContractRepository.class);
        Contract contract = contractRepository.getContractByNumber(contractNumber);
        if ( contract != null ) {
            contract.setBenefit(newBenefit);
            Contract modifiedContract = new ContractCalculation().calculatePremium(contract);
            contractRepository.saveContract(modifiedContract);
            return modifiedContract;
        }
        else
            return null;
    }
}
