package de.fourtytwoways.onion.application.usecases.contract;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.application.repositories.ContractRepository;
import de.fourtytwoways.onion.application.repositories.RepositoryRegistry;
import de.fourtytwoways.onion.domain.entities.contract.Contract;
import de.fourtytwoways.onion.domain.entities.event.DomainEventPublisher;

import java.util.function.Function;

abstract class AbstractContractModificationService {
    protected Contract modifyContract(String contractNumber, Function<Contract, Contract> contractModificationFunction) {
        ContractRepository contractRepository = (ContractRepository) RepositoryRegistry.getInstance().getRepository(ContractRepository.class);
        Contract contract = contractRepository.getContractByNumber(contractNumber);
        if ( contract != null ) {
            Contract modifiedContract =
                    contractRepository.saveContract(contractModificationFunction.apply(contract));
            DomainEventPublisher.getInstance().publish(new ContractValuesChanged(modifiedContract));
            return modifiedContract;
        }
        else
            return null;
    }
}
