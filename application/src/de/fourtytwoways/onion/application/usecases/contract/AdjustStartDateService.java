package de.fourtytwoways.onion.application.usecases.contract;
// Copyright (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.application.repositories.ContractRepository;
import de.fourtytwoways.onion.application.repositories.RepositoryRegistry;
import de.fourtytwoways.onion.domain.entities.contract.Contract;
import de.fourtytwoways.onion.domain.usecases.contract.ContractDurationChange;

import java.time.LocalDate;

public class AdjustStartDateService extends AbstractContractModificationService {
    public Contract adjustStartDate(String contractNumber, LocalDate newStartDate) {
        return modifyContract(contractNumber,
                              contract -> new ContractDurationChange().adjustStartDate(contract, newStartDate));
    }
}
