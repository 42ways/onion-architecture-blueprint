package de.fourtytwoways.onion.infrastructure;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.application.repositories.*;
import de.fourtytwoways.onion.infrastructure.contracts.db.ExampleContractRepository;
import de.fourtytwoways.onion.infrastructure.documents.ExampleDocumentRepository;
import de.fourtytwoways.onion.infrastructure.enums.provider.ExampleEnumRepository;
import de.fourtytwoways.onion.infrastructure.people.db.ExamplePersonRepository;

public class ExampleTestRepositoryRegistration {
    public static void registerRepos() {
        Repository enumRepository = new ExampleEnumRepository();
        Repository personRepository = new ExamplePersonRepository();
        Repository contractRepository = new ExampleContractRepository();
        Repository documentRepository = new ExampleDocumentRepository();

        RepositoryRegistry.getInstance().
                registerRepository(EnumRepository.class, enumRepository).
                registerRepository(PersonRepository.class, personRepository).
                registerRepository(ContractRepository.class, contractRepository).
                registerRepository(DocumentRepository.class, documentRepository);
    }
}
