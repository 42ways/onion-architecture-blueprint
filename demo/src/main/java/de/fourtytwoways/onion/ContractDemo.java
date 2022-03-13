package de.fourtytwoways.onion;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.application.repositories.*;
import de.fourtytwoways.onion.application.usecases.contract.ChangePremiumService;
import de.fourtytwoways.onion.domain.entities.contract.Contract;
import de.fourtytwoways.onion.domain.entities.person.Person;
import de.fourtytwoways.onion.domain.usecases.contract.ContractCalculation;
import de.fourtytwoways.onion.domain.usecases.contract.ContractDurationChange;
import de.fourtytwoways.onion.domain.values.Money;
import de.fourtytwoways.onion.domain.values.enumeration.ComputationTarget;
import de.fourtytwoways.onion.domain.values.enumeration.EnumType;
import de.fourtytwoways.onion.domain.values.enumeration.Product;
import de.fourtytwoways.onion.domain.values.enumeration.Sex;
import de.fourtytwoways.onion.infrastructure.contracts.db.ExampleContractRepository;
import de.fourtytwoways.onion.infrastructure.documents.ExampleDocumentRepository;
import de.fourtytwoways.onion.infrastructure.enums.provider.ExampleEnumRepository;
import de.fourtytwoways.onion.infrastructure.people.db.ExamplePersonRepository;

import java.time.LocalDate;

public class ContractDemo {

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

    public static void main(String[] args) {

        registerRepos();

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

        c2 = new ContractCalculation().calculateBenefit(c2);
        System.out.println(c2);

        c2 = new ContractDurationChange().adjustEndDate(c2, LocalDate.of(2032, 3, 31));
        System.out.println(c2);
        c2 = new ContractCalculation().calculateBenefit(c2);
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
        c4 = new ContractCalculation().calculatePremium(c4);
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
                changePremium("42", Money.valueOf(3216.8, Money.Currency.USD));
        System.out.println(c7);

        Contract c8 = myContractRepository.getContractByNumber("42");
        System.out.println(c8);
    }

    private static void createTestPeople(EnumRepository myEnumRepository, PersonRepository myPersonRepository) {
        Sex male = (Sex) myEnumRepository.getEntryByKey(EnumType.SEX, "M").orElse(null);
        Person person1 = new Person(1, "Tom", "Cartoon", LocalDate.of(1966, 6, 6), male);
        myPersonRepository.savePerson(person1);
        Person person2 = new Person(2, "Jerry", "Sketch", LocalDate.of(1977, 7, 7), male);
        myPersonRepository.savePerson(person2);
    }
}
