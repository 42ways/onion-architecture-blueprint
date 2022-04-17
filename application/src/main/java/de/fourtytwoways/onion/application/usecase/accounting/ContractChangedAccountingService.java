package de.fourtytwoways.onion.application.usecase.accounting;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.application.repository.AccountingRepository;
import de.fourtytwoways.onion.application.repository.RepositoryRegistry;
import de.fourtytwoways.onion.application.usecase.contract.ContractValuesChanged;
import de.fourtytwoways.onion.domain.model.contract.Contract;
import de.fourtytwoways.onion.domain.event.DomainEvent;
import de.fourtytwoways.onion.domain.event.DomainEventSubscriber;
import de.fourtytwoways.onion.domain.model.person.Person;

public class ContractChangedAccountingService implements DomainEventSubscriber {
    @Override
    public void handleEvent(DomainEvent domainEvent) {
        if (ContractValuesChanged.class.equals(domainEvent.getClass())) {
            Contract contract = ((ContractValuesChanged) domainEvent).modifiedContract();
            Person customer = contract.getBeneficiary();
            AccountingRepository accountingRepository =
                    (AccountingRepository) RepositoryRegistry.getInstance().getRepository(AccountingRepository.class);
            // TODO: should we insist on having an accounting interface (i.e. fail with a NPE)
            //       or return here is no accounting interface is found?
            //       What are the advantages and disadvantages of both approaches?
            accountingRepository.updateContractInformationForCustomer(customer, contract);
        }
    }

    @Override
    public Class<?> subscribedToEventType() {
        return ContractValuesChanged.class;
    }
}
