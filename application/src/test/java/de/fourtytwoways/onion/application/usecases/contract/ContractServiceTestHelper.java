package de.fourtytwoways.onion.application.usecases.contract;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.application.repositories.*;
import de.fourtytwoways.onion.domain.entities.contract.Contract;
import de.fourtytwoways.onion.domain.entities.person.Person;
import de.fourtytwoways.onion.domain.values.Money;
import de.fourtytwoways.onion.domain.values.enumeration.Product;
import de.fourtytwoways.onion.domain.values.enumeration.Sex;
import org.junit.jupiter.api.BeforeEach;

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

    private Person createTestPerson() {
        Sex male = new Sex(2, "M", "Male");
        return new Person(42, "Tom", "Flint",
                          LocalDate.of(1966, 6, 6), male);
    }

    private Product createTestProduct() {
        return new Product(42, "TEST", "MyTestProduct");
    }

    protected Contract createTestContract(String contractNumber) {
        return ((ContractRepository)
                RepositoryRegistry.getInstance().getRepository(ContractRepository.class)).createContract(
                contractNumber,
                createTestProduct(),
                createTestPerson(),
                LocalDate.of(2022, 4, 1),
                LocalDate.of(2042, 3, 31),
                Money.valueOf(4711), Money.valueOf(19.71));
    }

    protected List<String> getDocumentPrintOutput() {
        TestDocumentRepository testDocumentRepository = (TestDocumentRepository) RepositoryRegistry.getInstance().getRepository(DocumentRepository.class);
        testDocumentRepository.printAllDocuments();
        return testDocumentRepository.getPrintOutput();
    }
}
