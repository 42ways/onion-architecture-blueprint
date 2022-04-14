package de.fourtytwoways.onion.infrastructure.contracts.db;

import de.fourtytwoways.onion.application.repositories.ContractRepository;
import de.fourtytwoways.onion.application.repositories.EnumRepository;
import de.fourtytwoways.onion.application.repositories.PersonRepository;
import de.fourtytwoways.onion.application.repositories.RepositoryRegistry;
import de.fourtytwoways.onion.domain.entities.contract.Contract;
import de.fourtytwoways.onion.domain.entities.person.Person;
import de.fourtytwoways.onion.domain.values.Money;
import de.fourtytwoways.onion.domain.values.enumeration.EnumType;
import de.fourtytwoways.onion.domain.values.enumeration.Product;
import de.fourtytwoways.onion.domain.values.enumeration.Sex;
import de.fourtytwoways.onion.infrastructure.ExampleTestRepositoryRegistration;
import de.fourtytwoways.onion.infrastructure.database.contracts.ContractDAO;
import de.fourtytwoways.onion.infrastructure.database.contracts.ContractDbMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ExampleContractRepositoryTest {

    @BeforeAll
    public static void setUp() {
        ExampleTestRepositoryRegistration.registerRepos();
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    void createContract() {
        EnumRepository myEnumRepository = (EnumRepository) RepositoryRegistry.getInstance().getRepository(EnumRepository.class);
        Product endowmentInsurance = (Product) myEnumRepository.getEntryByKey(EnumType.PRODUCT, "GV").orElse(null);

        ContractRepository contractRepository =
                (ContractRepository) RepositoryRegistry.getInstance().getRepository(ContractRepository.class);
        Object contract = contractRepository.createContract("4711",
                                                              endowmentInsurance, null,
                                                              LocalDate.of(2022, 1, 1),
                                                              LocalDate.of(2032, 1, 1),
                                                              Money.valueOf(31415.93),
                                                              Money.valueOf(3.14));
        assert contract instanceof Contract;
        assert contract instanceof ContractDbMapper;
        assert contract instanceof ContractDAO;
        assertEquals("4711", ((Contract)contract).getContractNumber());
        assertEquals(endowmentInsurance, ((Contract)contract).getProduct());
    }

    @Test
    void getContractByNumber() {
        EnumRepository myEnumRepository = (EnumRepository) RepositoryRegistry.getInstance().getRepository(EnumRepository.class);
        Product endowmentInsurance = (Product) myEnumRepository.getEntryByKey(EnumType.PRODUCT, "GV").orElse(null);

        PersonRepository personRepository =
                (PersonRepository) RepositoryRegistry.getInstance().getRepository(PersonRepository.class);
        Sex male = new Sex(2, "M", "Male");
        Person beneficiary = new Person(42, "Tom", "Flint",
                          LocalDate.of(1966, 6, 6), male);
        personRepository.savePerson(beneficiary);

        ContractRepository contractRepository =
                (ContractRepository) RepositoryRegistry.getInstance().getRepository(ContractRepository.class);
        Contract contract = contractRepository.createContract("4711",
                                                              endowmentInsurance, beneficiary,
                                                              LocalDate.of(2022, 1, 1),
                                                              LocalDate.of(2032, 1, 1),
                                                              Money.valueOf(31415.93),
                                                              Money.valueOf(3.14));
        Contract savedContract = contractRepository.saveContract(contract);
        assertEquals("4711", savedContract.getContractNumber());
        assertEquals(endowmentInsurance, savedContract.getProduct());
        assertEquals(beneficiary, savedContract.getBeneficiary());

        Contract contractInDb = contractRepository.getContractByNumber("4711");
        assert contractInDb instanceof Contract;
        assert contractInDb instanceof ContractDbMapper;
        assert contractInDb instanceof ContractDAO;
        assertEquals("4711", contractInDb.getContractNumber());
        assertEquals(endowmentInsurance, contractInDb.getProduct());
        assertEquals(beneficiary, contractInDb.getBeneficiary());
        assertEquals(savedContract, contractInDb);
    }

    @Test
    void saveContract() {
        EnumRepository myEnumRepository = (EnumRepository) RepositoryRegistry.getInstance().getRepository(EnumRepository.class);
        Product endowmentInsurance = (Product) myEnumRepository.getEntryByKey(EnumType.PRODUCT, "GV").orElse(null);

        ContractRepository contractRepository =
                (ContractRepository) RepositoryRegistry.getInstance().getRepository(ContractRepository.class);
        Contract contract = contractRepository.createContract("4711",
                                                              endowmentInsurance, null,
                                                              LocalDate.of(2022, 1, 1),
                                                              LocalDate.of(2032, 1, 1),
                                                              Money.valueOf(31415.93),
                                                              Money.valueOf(3.14));
        Contract savedContract = contractRepository.saveContract(contract);
        assert savedContract instanceof Contract;
        assert savedContract instanceof ContractDbMapper;
        assert savedContract instanceof ContractDAO;
        assertEquals("4711", savedContract.getContractNumber());
        assertEquals(endowmentInsurance, savedContract.getProduct());
    }
}