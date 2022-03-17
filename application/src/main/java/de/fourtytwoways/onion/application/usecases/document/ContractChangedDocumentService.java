package de.fourtytwoways.onion.application.usecases.document;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.application.repositories.DocumentRepository;
import de.fourtytwoways.onion.application.repositories.RepositoryRegistry;
import de.fourtytwoways.onion.application.usecases.contract.ContractValuesChanged;
import de.fourtytwoways.onion.domain.entities.event.DomainEvent;
import de.fourtytwoways.onion.domain.entities.event.DomainEventSubscriber;

import java.util.List;

public class ContractChangedDocumentService implements DomainEventSubscriber {
    @Override
    public void handleEvent(DomainEvent domainEvent) {
        if (ContractValuesChanged.class.equals(domainEvent.getClass())) {
            DocumentRepository documentRepository = (DocumentRepository) RepositoryRegistry.getInstance().getRepository(DocumentRepository.class);
            documentRepository.createDocuments(
                    List.of(documentRepository.getFirstPageType(), documentRepository.getPolicyType()),
                    ((ContractValuesChanged) domainEvent).modifiedContract());
        }
    }

    @Override
    public Class subscribedToEventType() {
        return ContractValuesChanged.class;
    }
}
