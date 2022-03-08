package de.fourtytwoways.onion.application.usecases.contract;// Copyright (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.application.repositories.ContractRepository;
import de.fourtytwoways.onion.application.repositories.DocumentRepository;
import de.fourtytwoways.onion.application.repositories.EnumRepository;
import de.fourtytwoways.onion.application.repositories.RepositoryRegistry;
import de.fourtytwoways.onion.domain.entities.contract.Contract;
import de.fourtytwoways.onion.domain.entities.document.Document;
import de.fourtytwoways.onion.domain.entities.enumeration.Product;
import org.junit.jupiter.api.BeforeEach;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

abstract public class ContractServiceTestHelper {

    @BeforeEach
    protected void initializeRepositories() {
        RepositoryRegistry.getInstance().registerRepository(EnumRepository.class, new TestEnumRepository());
        RepositoryRegistry.getInstance().registerRepository(ContractRepository.class, new TestContractRepository());
        RepositoryRegistry.getInstance().registerRepository(DocumentRepository.class, new TestDocumentRepository());
    }

    protected void saveContract(Contract contract) {
        ((ContractRepository)
                RepositoryRegistry.getInstance().getRepository(ContractRepository.class)).saveContract(contract);
    }

    protected Contract loadContract(String s) {
        return ((ContractRepository)
                RepositoryRegistry.getInstance().getRepository(ContractRepository.class)).getContractByNumber(s);
    }

    protected Contract createTestContract(String contractNumber) {
        return ((ContractRepository)
                RepositoryRegistry.getInstance().getRepository(ContractRepository.class)).createContract(
                contractNumber,
                new Product(42, "TEST", "MyTestProduct"),
                LocalDate.of(2022, 4, 1),
                LocalDate.of(2042, 3, 31),
                BigDecimal.valueOf(4711), BigDecimal.valueOf(19.71));
    }

    protected List<String> getDocumentPrintOutput() {
        TestDocumentRepository testDocumentRepository = (TestDocumentRepository) RepositoryRegistry.getInstance().getRepository(DocumentRepository.class);
        testDocumentRepository.printAllDocuments();
        return testDocumentRepository.getPrintOutput();
    }
}
