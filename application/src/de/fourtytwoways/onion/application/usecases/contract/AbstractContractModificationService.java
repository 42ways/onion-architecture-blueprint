package de.fourtytwoways.onion.application.usecases.contract;
// Copyright (c) 2022 Thomas Herrmann, 42ways GmbH

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
            contractRepository.saveContract(modifiedContract);
            DocumentRepository documentRepository = (DocumentRepository) RepositoryRegistry.getInstance().getRepository(DocumentRepository.class);
            documentRepository.createDocument(documentRepository.getPolicyType(), modifiedContract);
            return modifiedContract;
        }
        else
            return null;
    }
}
