package de.fourtytwoways.onion.infrastructure.database.people;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import com.google.common.collect.ImmutableList;
import de.fourtytwoways.onion.application.repositories.EnumRepository;
import de.fourtytwoways.onion.application.repositories.RepositoryRegistry;
import de.fourtytwoways.onion.domain.entities.person.Address;
import de.fourtytwoways.onion.domain.entities.person.BankAccount;
import de.fourtytwoways.onion.domain.entities.person.Person;
import de.fourtytwoways.onion.domain.values.enumeration.EnumType;
import de.fourtytwoways.onion.domain.values.enumeration.Sex;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

    PersonDAO(Person person) {
        this.id = person.getId();
        this.name = person.getName();
        this.surname = person.getSurname();
        this.birthday = person.getBirthday();
        this.sex = person.getSex().getKey();
        for (Address address : person.getAddresses()) {
            addressDAOS.add(new AddressDAO(this, address));
        }
        for (BankAccount bankAccount : person.getBankAccounts()) {
            bankAccountDAOS.add(new BankAccountDAO(this, bankAccount));
        }
    }

    private static EnumRepository enumRepository;

    static EnumRepository getEnumRepository() {
        if (enumRepository == null)
            enumRepository = (EnumRepository) RepositoryRegistry.getInstance().getRepository(EnumRepository.class);
        return enumRepository;
    }

    Person toPerson() {
        List<Address> addresses = new ArrayList<>();
        for (AddressDAO addressDAO : addressDAOS) {
            addresses.add(addressDAO.toAddress());
        }
        List<BankAccount> bankAccounts = new ArrayList<>();
        for (BankAccountDAO bankAccountDAO : bankAccountDAOS) {
            bankAccounts.add(bankAccountDAO.toBankAccount());
        }
        return Person.builder()
                .id(id)
                .name(name)
                .surname(surname)
                .birthday(birthday)
                .sex((Sex) getEnumRepository().getEntryByKey(EnumType.SEX, this.sex).get())
                .addresses(ImmutableList.copyOf(addresses))
                .bankAccounts(ImmutableList.copyOf(bankAccounts))
                .build();
    }
}
