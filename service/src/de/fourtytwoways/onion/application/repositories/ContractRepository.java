package de.fourtytwoways.onion.application.repositories;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.domain.entities.contract.Contract;
import de.fourtytwoways.onion.domain.entities.enumeration.Product;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface ContractRepository extends Repository {
    Contract createContract(String contractNumber, Product product, LocalDate beginDate, LocalDate endDate, BigDecimal benefit, BigDecimal premium);

    Contract getContractByNumber(String contractNumber);

    boolean saveContract(Contract contract);
}
