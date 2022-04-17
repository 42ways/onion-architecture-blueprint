package de.fourtytwoways.onion.application.repository;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.domain.model.contract.Contract;
import de.fourtytwoways.onion.domain.model.person.Person;

public interface AccountingRepository extends Repository {
    void updateContractInformationForCustomer(Person customer, Contract contract);
}
