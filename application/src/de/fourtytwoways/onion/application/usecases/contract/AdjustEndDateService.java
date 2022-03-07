package de.fourtytwoways.onion.application.usecases.contract;
// Copyright (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.application.repositories.ContractRepository;
import de.fourtytwoways.onion.application.repositories.RepositoryRegistry;
import de.fourtytwoways.onion.domain.entities.contract.Contract;
import de.fourtytwoways.onion.domain.usecases.contract.ContractDurationChange;

import java.time.LocalDate;
import java.util.function.Function;

public class AdjustEndDateService extends AbstractContractModificationService {
    public Contract adjustEndDate(String contractNumber, LocalDate newEndDate) {
        return modifyContract(contractNumber,
                              contract -> new ContractDurationChange().adjustEndDate(contract, newEndDate));
    }
}
