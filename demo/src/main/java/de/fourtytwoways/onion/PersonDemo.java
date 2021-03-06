package de.fourtytwoways.onion;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.application.repository.EnumRepository;
import de.fourtytwoways.onion.application.repository.PersonRepository;
import de.fourtytwoways.onion.application.repository.Repository;
import de.fourtytwoways.onion.application.repository.RepositoryRegistry;
import de.fourtytwoways.onion.domain.model.person.Address;
import de.fourtytwoways.onion.domain.model.person.BankAccount;
import de.fourtytwoways.onion.domain.model.person.Person;
import de.fourtytwoways.onion.domain.model.enumeration.EnumType;
import de.fourtytwoways.onion.domain.model.enumeration.Sex;
import de.fourtytwoways.onion.infrastructure.provider.enumeration.ExampleEnumRepository;
import de.fourtytwoways.onion.infrastructure.database.person.ExamplePersonRepository;
import org.iban4j.CountryCode;
import org.iban4j.Iban;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PersonDemo {

    public static void registerRepos() {
        Repository enumRepository = new ExampleEnumRepository();
        Repository personRepository = new ExamplePersonRepository();

        RepositoryRegistry.getInstance().
                registerRepository(EnumRepository.class, enumRepository).
                registerRepository(PersonRepository.class, personRepository);
    }

    public static void main(String[] args) {
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(java.util.logging.Level.SEVERE);

        Logger logger = LoggerFactory.getLogger(ContractDemo.class);
        logger.info("PersonDemo started");

        registerRepos();

        // TODO: Can we get rid of this cast?
        EnumRepository myEnumRepository = (EnumRepository) RepositoryRegistry.getInstance().getRepository(EnumRepository.class);
        PersonRepository myPersonRepository = (PersonRepository) RepositoryRegistry.getInstance().getRepository(PersonRepository.class);

        deleteDemoPeople();

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

        List<Person> people = myPersonRepository.getPeopleByName(null, null);

        for (Person person : people) {
            System.out.println(person);
        }

        Person p5 = myPersonRepository.getPersonById(5);
        System.out.println(p5);

        Person p1 = myPersonRepository.getPersonById(1);
        System.out.println(p1);

        Person p6 = p1.addAddress(Address.builder()
                                          .primary(true)
                                          .street("Rodeo Boulevard")
                                          .number("42")
                                          .zipCode("88776")
                                          .city("Mytown")
                                          .build());
        System.out.println(p6);
        myPersonRepository.savePerson(p6);

        Person p7 = myPersonRepository.getPersonById(1);
        System.out.println(p7);

        Person person3 =
                person2.addAddress(Address.builder()
                                           .primary(true)
                                           .street("Main Street")
                                           .number("1")
                                           .zipCode("87654")
                                           .city("High Noon Town")
                                           .build())
                        .addBankAccount(BankAccount.builder()
                                                .primary(true)
                                                .accountHolderName("Jerry")
                                                .bankName("First Savings Bank")
                                                .iban(new Iban.Builder()
                                                              .countryCode(CountryCode.DE)
                                                              .bankCode("89370400")
                                                              .accountNumber("4405320130")
                                                              .build()
                                                              .toString())
                                                .bic("GOBAYERN")
                                                .build());
        myPersonRepository.savePerson(person3);
        System.out.println(person3);

        Person p2 = p1.addAddress(Address.builder()
                                          .primary(false)
                                          .street("Oasisroad")
                                          .number("5")
                                          .zipCode("76543")
                                          .city("Dessert Island")
                                          .build());
        System.out.println(p2);
        myPersonRepository.savePerson(p2);

        Person p3 = myPersonRepository.getPersonById(1);
        System.out.println(p3);

        Person p4 = myPersonRepository.getPersonById(2);
        System.out.println(p4);
    }

    private static void deleteDemoPeople() {
        PersonRepository myPersonRepository = (PersonRepository) RepositoryRegistry.getInstance().getRepository(PersonRepository.class);
        for (int i = 1; i <= 2; i++) {
            Person p = myPersonRepository.getPersonById(i);
            if (p != null) {
                myPersonRepository.deletePerson(p);
                System.out.println("Deleting person " + p);
            }
        }
    }
}
