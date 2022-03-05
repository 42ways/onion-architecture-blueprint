package de.fourtytwoways.onion.application;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.domain.model.contracts.Contract;
import de.fourtytwoways.onion.domain.model.enums.Product;

import java.time.LocalDate;

public interface ContractRepository extends Repository {
    Contract createContract(String contractNumber, Product product, LocalDate beginDate, LocalDate endDate, double premium);

    Contract getContractByNumber(String contractNumber);

    boolean saveContract(Contract contract);
}
