package de.fourtytwoways.onion.application.usecase.contract;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.application.repository.ContractRepository;
import de.fourtytwoways.onion.application.repository.RepositoryRegistry;
import de.fourtytwoways.onion.domain.model.contract.Contract;
import de.fourtytwoways.onion.domain.event.DomainEventPublisher;

import java.time.LocalDate;
import java.util.function.Function;

abstract class AbstractContractModificationService {
    protected Contract modifyContract(String contractNumber, Function<Contract, Contract> contractModificationFunction) {
        ContractRepository contractRepository = (ContractRepository) RepositoryRegistry.getInstance().getRepository(ContractRepository.class);
        Contract contract = contractRepository.getContractByNumber(contractNumber);
        if ( contract != null ) {
            Contract modifiedContract =
                    contractRepository.saveContract(contractModificationFunction.apply(contract));
            DomainEventPublisher.getInstance().publish(new ContractValuesChanged(LocalDate.now(), modifiedContract));
            return modifiedContract;
        }
        else
            return null;
    }
}
