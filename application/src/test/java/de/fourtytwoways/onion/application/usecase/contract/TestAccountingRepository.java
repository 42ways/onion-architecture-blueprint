package de.fourtytwoways.onion.application.usecase.contract;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.application.repository.AccountingRepository;
import de.fourtytwoways.onion.domain.model.contract.Contract;
import de.fourtytwoways.onion.domain.model.person.Person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestAccountingRepository implements AccountingRepository {
    private record AccountingInterfaceData(Person customer, Contract contract) {}
    private final List<AccountingInterfaceData> interfaceData = new ArrayList<>();
    @Override
    public void updateContractInformationForCustomer(Person customer, Contract contract) {
        interfaceData.add(new AccountingInterfaceData(customer, contract));
    }
    public List<AccountingInterfaceData> getInterfaceData() {
        return Collections.unmodifiableList(interfaceData);
    }
}
