package de.fourtytwoways.onion.infrastructure.contracts.db;

import de.fourtytwoways.onion.domain.entities.contract.Contract;
import de.fourtytwoways.onion.domain.entities.person.Person;
import de.fourtytwoways.onion.domain.values.Money;
import de.fourtytwoways.onion.domain.values.enumeration.Product;

import java.time.LocalDate;

public class ContractDbMapper extends Contract {

    protected ContractDbMapper() {
    }

    public ContractDbMapper(String contractNumber, Product product, Person beneficiary, LocalDate beginDate, LocalDate endDate, Money benefit, Money premium) {
        super(contractNumber, product, beneficiary, beginDate, endDate, benefit, premium);
    }
}
