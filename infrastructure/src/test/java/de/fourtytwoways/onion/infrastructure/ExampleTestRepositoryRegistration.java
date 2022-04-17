package de.fourtytwoways.onion.infrastructure;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.application.repository.*;
import de.fourtytwoways.onion.infrastructure.adapter.document.ExampleDocumentRepository;
import de.fourtytwoways.onion.infrastructure.database.contract.ExampleContractRepository;
import de.fourtytwoways.onion.infrastructure.database.person.ExamplePersonRepository;
import de.fourtytwoways.onion.infrastructure.provider.enumeration.ExampleEnumRepository;

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
