package de.fourtytwoways.onion.application.repository;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.domain.model.contract.Contract;
import de.fourtytwoways.onion.domain.model.person.Person;
import de.fourtytwoways.onion.domain.model.asset.Money;
import de.fourtytwoways.onion.domain.model.enumeration.Product;

import java.time.LocalDate;

public interface ContractRepository extends Repository {
    Contract createContract(String contractNumber, Product product, Person beneficiary, LocalDate startDate, LocalDate endDate, Money benefit, Money premium);

    Contract getContractByNumber(String contractNumber);

    Contract saveContract(Contract contract);
}
