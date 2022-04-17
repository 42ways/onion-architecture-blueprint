package de.fourtytwoways.onion.application.usecase.contract;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.domain.model.contract.Contract;
import de.fourtytwoways.onion.domain.usecase.contract.ContractDurationChange;

import java.time.LocalDate;

public class AdjustEndDateService extends AbstractContractModificationService {
    public Contract adjustEndDate(String contractNumber, LocalDate newEndDate) {
        return modifyContract(contractNumber,
                              contract -> new ContractDurationChange().adjustEndDate(contract, newEndDate));
    }
}
