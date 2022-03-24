package de.fourtytwoways.onion.infrastructure.people.db;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.domain.entities.person.Address;
import de.fourtytwoways.onion.domain.entities.person.BankAccount;
import de.fourtytwoways.onion.domain.entities.person.Person;
import de.fourtytwoways.onion.domain.values.enumeration.EnumType;
import de.fourtytwoways.onion.domain.values.enumeration.Sex;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "PEOPLE")
public class PersonDAO {
    @Id
    int id;
    String name;
    String surname;
    LocalDate birthday;
    String sex;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "personDAO")
    final Collection<AddressDAO> addressDAOS = new ArrayList<>();
    // Using FetchType.EAGER here too would cause a MultipleBagFetchException in Hibernate
    // However, since we copy all person data into the domain model during loading the data
    // for a use case, this shouldn't be a problem
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "personDAO")
    final Collection<BankAccountDAO> bankAccountDAOS = new ArrayList<>();

    public PersonDAO() {
    }

    PersonDAO(int id, String name, String surname, LocalDate birthday, String sex,
              Iterable<Address> addresses, Iterable<BankAccount> bankAccounts) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
        this.sex = sex;
        for (Address address : addresses) {
            addressDAOS.add(new AddressDAO(this, address));
        }
        for (BankAccount bankAccount : bankAccounts) {
            bankAccountDAOS.add(new BankAccountDAO(this, bankAccount));
        }
    }

    Person toPerson() {
        Sex sex = (Sex) ExamplePersonRepository.getEnumRepository().getEntryByKey(EnumType.SEX, this.sex).orElse(null);
        Person person = new Person(id, name, surname, birthday, sex);
        for (AddressDAO addressDAO : addressDAOS) {
            person.addAddress(addressDAO.toAddress());
        }
        for (BankAccountDAO bankAccountDAO : bankAccountDAOS) {
            person.addBankAccount(bankAccountDAO.toBankAccount());
        }
        return person;
    }
}
