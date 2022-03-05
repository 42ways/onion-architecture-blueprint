package de.fourtytwoways.onion.contracts;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.Repository;
import de.fourtytwoways.onion.enums.types.Product;

import java.time.LocalDate;

public interface ContractRepository extends Repository {
    Contract createContract(String contractNumber, Product product, LocalDate beginDate, LocalDate endDate, double premium);

    Contract getContractByNumber(String contractNumber);

    boolean saveContract(Contract contract);
}
