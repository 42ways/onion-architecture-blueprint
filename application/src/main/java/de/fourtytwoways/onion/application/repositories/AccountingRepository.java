package de.fourtytwoways.onion.application.repositories;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.domain.entities.contract.Contract;
import de.fourtytwoways.onion.domain.entities.person.Person;

public interface AccountingRepository extends Repository {
    void updateContractInformationForCustomer(Person customer, Contract contract);
}
