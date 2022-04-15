package de.fourtytwoways.onion.infrastructure.people;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.application.repositories.PersonRepository;
import de.fourtytwoways.onion.application.repositories.RepositoryRegistry;
import de.fourtytwoways.onion.domain.entities.person.Address;
import de.fourtytwoways.onion.domain.entities.person.BankAccount;
import de.fourtytwoways.onion.domain.entities.person.Person;
import de.fourtytwoways.onion.domain.values.enumeration.Sex;
import de.fourtytwoways.onion.infrastructure.ExampleTestRepositoryRegistration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ExamplePersonRepositoryTest {

    @BeforeAll
    public static void setUp() {
        ExampleTestRepositoryRegistration.registerRepos();
    }

    @AfterEach
    public void tearDown() {
        PersonRepository personRepository =
                (PersonRepository) RepositoryRegistry.getInstance().getRepository(PersonRepository.class);
        Person p = personRepository.getPersonById(firstTestPerson().getId());
        if (p != null)
            personRepository.deletePerson(p);
    }

    @Test
    void getPersonById() {
        storeTestPerson();

        PersonRepository personRepository =
                (PersonRepository) RepositoryRegistry.getInstance().getRepository(PersonRepository.class);

        assertNull(personRepository.getPersonById(1));
        assertNotNull(personRepository.getPersonById(42));

        Person p = personRepository.getPersonById(42);
        assertEquals("Tom", p.getName());
        assertEquals("Flint", p.getSurname());
        assertEquals(1, p.getAddresses().size());
        assertEquals(1, p.getBankAccounts().size());
        assertEquals("Person(id=XX, name=Tom, surname=Flint, birthday=1966-06-06, sex=Sex(2, M, Male)," +
                             " addresses=[Address(id=XX, primary=true, street=Main Street, number=42," +
                             " zipCode=12345, city=Myhometown)]," +
                             " bankAccounts=[BankAccount(id=XX, primary=true, accountHolderName=Tom Flint," +
                             " bankName=Garden Onion Bank, iban=123456789, bic=GOB123X)])",
                     neutralizeIds(p.toString()));
    }

    @Test
    void getPeopleByName() {
        storeTestPerson();

        PersonRepository personRepository =
                (PersonRepository) RepositoryRegistry.getInstance().getRepository(PersonRepository.class);

        assertEquals(1, personRepository.getPeopleByName(null, null).size());
        assertEquals(1, personRepository.getPeopleByName(null, "Flint").size());
        assertEquals(1, personRepository.getPeopleByName("Tom", null).size());
        assertEquals(1, personRepository.getPeopleByName("Tom", "Flint").size());
        assertEquals(0, personRepository.getPeopleByName(null, "Stone").size());

        Sex female = new Sex(1, "F", "Female");
        Person wilma = new Person(11, "Wilma", "Flint",
                          LocalDate.of(1967, 8, 9), female);
        Person storedWilma = personRepository.savePerson(wilma);
        assertEquals(storedWilma, wilma);

        assertEquals(1, personRepository.getPeopleByName("Wilma", "Flint").size());
        assertEquals(1, personRepository.getPeopleByName("Tom", null).size());
        assertEquals(1, personRepository.getPeopleByName("Wilma", null).size());
        assertEquals(2, personRepository.getPeopleByName(null, "Flint").size());
        assertEquals(2, personRepository.getPeopleByName(null, null).size());

        Person dbWilma = personRepository.getPersonById(11);
        personRepository.deletePerson(dbWilma);
    }

    @Test
    void savePerson() {
        PersonRepository personRepository =
                (PersonRepository) RepositoryRegistry.getInstance().getRepository(PersonRepository.class);

        Person p = firstTestPerson().addAddress(firstAddress()).addBankAccount(firstBankAccount());

        Person storedP = personRepository.savePerson(p);
        assertEquals(1, personRepository.getPeopleByName(null, null).size());

        Person dbPerson = personRepository.getPersonById(storedP.getId());
        assertEquals("Person(id=XX, name=Tom, surname=Flint, birthday=1966-06-06, sex=Sex(2, M, Male)," +
                             " addresses=[Address(id=XX, primary=true, street=Main Street, number=42," +
                             " zipCode=12345, city=Myhometown)]," +
                             " bankAccounts=[BankAccount(id=XX, primary=true, accountHolderName=Tom Flint," +
                             " bankName=Garden Onion Bank, iban=123456789, bic=GOB123X)])",
                     neutralizeIds(dbPerson.toString()));

        Person modifiedDbPerson = dbPerson.addBankAccount(secondBankAccount()).addAddress(secondAddress());

        Person storedPerson = personRepository.savePerson(modifiedDbPerson);
        assertEquals(1, personRepository.getPeopleByName(null, null).size());

        Person reloadedPerson = personRepository.getPersonById(p.getId());
        assertEquals(2, reloadedPerson.getAddresses().size());
        assertEquals(2, reloadedPerson.getBankAccounts().size());
        assertEquals(storedPerson.toString(), reloadedPerson.toString());
        assertEquals("Person(id=XX, name=Tom, surname=Flint, birthday=1966-06-06, sex=Sex(2, M, Male)," +
                             " addresses=[Address(id=XX, primary=true, street=Main Street, number=42," +
                             " zipCode=12345, city=Myhometown)," +
                             " Address(id=XX, primary=false, street=Sunset Strip, number=77," +
                             " zipCode=77555, city=Sunny Village)]," +
                             " bankAccounts=[BankAccount(id=XX, primary=true, accountHolderName=Tom Flint," +
                             " bankName=Garden Onion Bank, iban=123456789, bic=GOB123X)," +
                             " BankAccount(id=XX, primary=false, accountHolderName=Flint familiy account," +
                             " bankName=Shallot Savings, iban=123654789, bic=SHA1SAV)])",
                     neutralizeIds(reloadedPerson.toString()));
    }

    @Test
    void deletePerson() {
        storeTestPerson();

        PersonRepository personRepository =
                (PersonRepository) RepositoryRegistry.getInstance().getRepository(PersonRepository.class);

        assertNotNull(personRepository.getPersonById(42));

        personRepository.deletePerson(personRepository.getPersonById(42));
        assertNull(personRepository.getPersonById(42));
    }

    private void storeTestPerson() {
        PersonRepository personRepository =
                (PersonRepository) RepositoryRegistry.getInstance().getRepository(PersonRepository.class);

        Person p = firstTestPerson().addAddress(firstAddress()).addBankAccount(firstBankAccount());

        personRepository.savePerson(p);
    }

    private String neutralizeIds(String s) {
        return s.replaceAll("id=[0-9]+", "id=XX");
    }

    private Person firstTestPerson() {
        Sex male = new Sex(2, "M", "Male");
        return new Person(42, "Tom", "Flint",
                          LocalDate.of(1966, 6, 6), male);
    }

    private Address firstAddress() {
        return Address.builder()
                .primary(true)
                .street("Main Street")
                .number("42")
                .zipCode("12345")
                .city("Myhometown")
                .build();
    }

    private Address secondAddress() {
        return Address.builder()
                .primary(false)
                .street("Sunset Strip")
                .number("77")
                .zipCode("77555")
                .city("Sunny Village")
                .build();
    }

    private BankAccount firstBankAccount() {
        return BankAccount.builder()
                .primary(true)
                .accountHolderName("Tom Flint")
                .bankName("Garden Onion Bank")
                .iban("123456789")
                .bic("GOB123X")
                .build();
    }

    private BankAccount secondBankAccount() {
        return BankAccount.builder()
                .primary(false)
                .accountHolderName("Flint familiy account")
                .bankName("Shallot Savings")
                .iban("123654789")
                .bic("SHA1SAV")
                .build();
    }
}