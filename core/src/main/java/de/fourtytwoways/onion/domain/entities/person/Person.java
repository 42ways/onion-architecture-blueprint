package de.fourtytwoways.onion.domain.entities.person;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.domain.values.enumeration.Sex;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class Person {
    private final int id;
    private String name;
    private String surname;
    private LocalDate birthday;
    private Sex sex;
    private final List<Address> addresses = new ArrayList<>();
    private final List<BankAccount> bankAccounts = new ArrayList<>();

    public Person(int id, String name, String surname, LocalDate birthday, Sex sex) {
        this.id = id;
        this.setName(name);
        this.setSurname(surname);
        this.setBirthday(birthday);
        this.setSex(sex);
    }

    public Person addAddress(Address address) {
        addresses.add(address);
        return this;
    }

    public Person removeAddress(Address address) {
        addresses.remove(address);
        return this;
    }

    public Person addBankAccount(BankAccount bankAccount) {
        bankAccounts.add(bankAccount);
        return this;
    }

    public Person removeBankAccount(BankAccount bankAccount) {
        bankAccounts.remove(bankAccount);
        return this;
    }

}
