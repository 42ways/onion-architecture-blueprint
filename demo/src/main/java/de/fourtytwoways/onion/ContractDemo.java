package de.fourtytwoways.onion;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.application.repository.*;
import de.fourtytwoways.onion.application.usecase.contract.ChangePremiumService;
import de.fourtytwoways.onion.application.usecase.document.ContractChangedDocumentService;
import de.fourtytwoways.onion.domain.model.contract.Contract;
import de.fourtytwoways.onion.domain.event.DomainEventPublisher;
import de.fourtytwoways.onion.domain.model.person.Person;
import de.fourtytwoways.onion.domain.usecase.contract.ContractCalculation;
import de.fourtytwoways.onion.domain.usecase.contract.ContractDurationChange;
import de.fourtytwoways.onion.domain.model.asset.Money;
import de.fourtytwoways.onion.domain.model.enumeration.ComputationTarget;
import de.fourtytwoways.onion.domain.model.enumeration.EnumType;
import de.fourtytwoways.onion.domain.model.enumeration.Product;
import de.fourtytwoways.onion.domain.model.enumeration.Sex;
import de.fourtytwoways.onion.infrastructure.database.contract.ExampleContractRepository;
import de.fourtytwoways.onion.infrastructure.adapter.document.ExampleDocumentRepository;
import de.fourtytwoways.onion.infrastructure.provider.enumeration.ExampleEnumRepository;
import de.fourtytwoways.onion.infrastructure.database.person.ExamplePersonRepository;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ContractDemo {

    public static void registerReposAndEventPublishers() {
        Repository enumRepository = new ExampleEnumRepository();
        Repository personRepository = new ExamplePersonRepository();
        Repository contractRepository = new ExampleContractRepository();
        Repository documentRepository = new ExampleDocumentRepository();

        RepositoryRegistry.getInstance().
                registerRepository(EnumRepository.class, enumRepository).
                registerRepository(PersonRepository.class, personRepository).
                registerRepository(ContractRepository.class, contractRepository).
                registerRepository(DocumentRepository.class, documentRepository);

        DomainEventPublisher.getInstance().clearSubscribers().subscribe(new ContractChangedDocumentService());
    }

    public static void main(String[] args) {
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(java.util.logging.Level.SEVERE);

        Logger logger = LoggerFactory.getLogger(ContractDemo.class);
        logger.info("ContractDemo started");

        registerReposAndEventPublishers();

        EnumRepository myEnumRepository = (EnumRepository) RepositoryRegistry.getInstance().getRepository(EnumRepository.class);
        ContractRepository myContractRepository = (ContractRepository) RepositoryRegistry.getInstance().getRepository(ContractRepository.class);
        PersonRepository myPersonRepository = (PersonRepository) RepositoryRegistry.getInstance().getRepository(PersonRepository.class);

        createTestPeople(myEnumRepository, myPersonRepository);

        Product endowmentInsurance = (Product) myEnumRepository.getEntryByKey(EnumType.PRODUCT, "GV").orElse(null);

        Contract c1 =
                myContractRepository.createContract("42",
                                                    endowmentInsurance,
                                                    myPersonRepository.getPersonById(1),
                                                    LocalDate.of(2022, 4, 1), LocalDate.of(2042, 3, 31),
                                                    null /* calculate this later */,
                                                    Money.valueOf(666.));
        Contract savedContract = myContractRepository.saveContract(c1);
        System.out.println(savedContract);

        Contract c2 = myContractRepository.getContractByNumber("42");
        System.out.println(c2);

        c2 = new ContractCalculation().calculate(c2, ComputationTarget.BENEFIT);
        System.out.println(c2);

        c2 = new ContractDurationChange().adjustEndDate(c2, LocalDate.of(2032, 3, 31));
        System.out.println(c2);
        c2 = new ContractCalculation().calculate(c2, ComputationTarget.BENEFIT);
        System.out.println(c2);
        myContractRepository.saveContract(c2);

        Contract c3 = myContractRepository.getContractByNumber("42");
        System.out.println(c3);

        Contract c4 =
                myContractRepository.createContract("0815",
                                                    endowmentInsurance,
                                                    myPersonRepository.getPersonById(2),
                                                    LocalDate.of(2022, 4, 1), LocalDate.of(2042, 3, 31),
                                                    Money.valueOf(4711), null /* calculate later */);
        System.out.println(c4);
        c4 = new ContractCalculation().calculate(c4, ComputationTarget.PREMIUM);
        System.out.println(c4);
        myContractRepository.saveContract(c4);

        c4 = new ContractDurationChange().adjustStartDate(c4, LocalDate.of(2027, 4, 1), ComputationTarget.PREMIUM);
        System.out.println(c4);
        myContractRepository.saveContract(c4);

        Contract c5 = myContractRepository.getContractByNumber("42");
        System.out.println(c5);

        Contract c6 = myContractRepository.getContractByNumber("0815");
        System.out.println(c6);

        Contract c7 = new ChangePremiumService().
                changePremium("42", Money.valueOf(3216.8, Money.USD));
        System.out.println(c7);

        Contract c8 = myContractRepository.getContractByNumber("42");
        System.out.println(c8);
    }

    private static void createTestPeople(EnumRepository myEnumRepository, PersonRepository myPersonRepository) {
        Person person1 =
                Person.builder()
                        .id(1)
                        .name("Tom")
                        .surname("Cartoon")
                        .birthday(LocalDate.of(1966, 6, 6))
                        .sex((Sex) myEnumRepository.getEntryByKey(EnumType.SEX, "M").get())
                        .build();
        myPersonRepository.savePerson(person1);
        Person person2 =
                Person.builder()
                        .id(2)
                        .name("Jerry")
                        .surname("Sketch")
                        .birthday(LocalDate.of(1977, 7, 7))
                        .sex((Sex) myEnumRepository.getEntryByKey(EnumType.SEX, "F").get())
                        .build();
        myPersonRepository.savePerson(person2);
    }
}
