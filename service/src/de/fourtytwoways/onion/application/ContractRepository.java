package de.fourtytwoways.onion.application;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.domain.model.contracts.Contract;
import de.fourtytwoways.onion.domain.model.enums.Product;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface ContractRepository extends Repository {
    Contract createContract(String contractNumber, Product product, LocalDate beginDate, LocalDate endDate, BigDecimal benefit, BigDecimal premium);

    Contract getContractByNumber(String contractNumber);

    boolean saveContract(Contract contract);
}
