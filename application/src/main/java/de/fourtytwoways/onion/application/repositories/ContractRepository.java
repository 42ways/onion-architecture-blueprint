package de.fourtytwoways.onion.application.repositories;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.domain.entities.contract.Contract;
import de.fourtytwoways.onion.domain.values.Money;
import de.fourtytwoways.onion.domain.values.enumeration.Product;

import java.time.LocalDate;

public interface ContractRepository extends Repository {
    Contract createContract(String contractNumber, Product product, LocalDate startDate, LocalDate endDate, Money benefit, Money premium);

    Contract getContractByNumber(String contractNumber);

    void saveContract(Contract contract);
}
