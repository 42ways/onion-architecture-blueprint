package de.fourtytwoways.onion.application.usecases.contract;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.application.repositories.ContractRepository;
import de.fourtytwoways.onion.application.repositories.DocumentRepository;
import de.fourtytwoways.onion.application.repositories.RepositoryRegistry;
import de.fourtytwoways.onion.domain.entities.contract.Contract;

import java.util.function.Function;

abstract class AbstractContractModificationService {
    protected Contract modifyContract(String contractNumber, Function<Contract, Contract> contractModificationFunction) {
        ContractRepository contractRepository = (ContractRepository) RepositoryRegistry.getInstance().getRepository(ContractRepository.class);
        Contract contract = contractRepository.getContractByNumber(contractNumber);
        if ( contract != null ) {
            Contract modifiedContract = contractModificationFunction.apply(contract);
            Contract storedModifiedContract = contractRepository.saveContract(modifiedContract);
            DocumentRepository documentRepository = (DocumentRepository) RepositoryRegistry.getInstance().getRepository(DocumentRepository.class);
            // TODO: In a real system, this would be managed by a rule based document component
            documentRepository.createDocument(documentRepository.getFirstPageType(), storedModifiedContract);
            documentRepository.createDocument(documentRepository.getPolicyType(), storedModifiedContract);
            return storedModifiedContract;
        }
        else
            return null;
    }
}
