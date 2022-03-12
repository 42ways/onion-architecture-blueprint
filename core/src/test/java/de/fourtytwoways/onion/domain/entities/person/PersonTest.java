package de.fourtytwoways.onion.domain.entities.person;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.domain.values.enumeration.Sex;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PersonTest {

    private Person createTestPerson() {
        Sex male = new Sex(2, "M", "Male");
        return new Person(42, "Tom", "Flint",
                          LocalDate.of(1966, 6, 6), male);
    }

    private Address firstAddress() {
        return new Address(true, "Main Street", "42",
                           "12345", "Myhometown");
    }

    private Address secondAddress() {
        return new Address(false, "Sunset Strip", "77",
                           "77555", "Sunny Village");
    }

    private BankAccount firstBankAccount() {
        return new BankAccount(true, "Tom Flint", "Garden Onion Bank",
                               "123456789", "GOB123X");
    }

    private BankAccount secondBankAccount() {
        return new BankAccount(false, "Flint familiy account", "Shallot Savings",
                               "123654789", "SHA1SAV");
    }

    @Test
    void addAddress() {
        Person p = createTestPerson();
        assertEquals(0, p.getAddresses().size());
        p.addAddress(firstAddress());
        assertEquals(1, p.getAddresses().size());
        assertEquals("Person(id=42, name=Tom, surname=Flint, birthday=1966-06-06, sex=Sex(2, M, Male)," +
                             " addresses=[Address(id=0, primary=true, street=Main Street, number=42, zipCode=12345, city=Myhometown)], bankAccounts=[])",
                     p.toString());
        p.addAddress(secondAddress());
        assertEquals(2, p.getAddresses().size());
        assertEquals("Person(id=42, name=Tom, surname=Flint, birthday=1966-06-06, sex=Sex(2, M, Male)," +
                             " addresses=[Address(id=0, primary=true, street=Main Street, number=42, zipCode=12345, city=Myhometown)," +
                             " Address(id=0, primary=false, street=Sunset Strip, number=77, zipCode=77555, city=Sunny Village)], bankAccounts=[])",
                     p.toString());
    }

    @Test
    void removeAddress() {
        Person p = createTestPerson();
        p.addAddress(firstAddress());
        p.addAddress(secondAddress());
        assertEquals(2, p.getAddresses().size());
        assertEquals("Person(id=42, name=Tom, surname=Flint, birthday=1966-06-06, sex=Sex(2, M, Male)," +
                             " addresses=[Address(id=0, primary=true, street=Main Street, number=42, zipCode=12345, city=Myhometown)," +
                             " Address(id=0, primary=false, street=Sunset Strip, number=77, zipCode=77555, city=Sunny Village)], bankAccounts=[])",
                     p.toString());
        p.removeAddress(firstAddress());
        assertEquals(1, p.getAddresses().size());
        assertEquals("Person(id=42, name=Tom, surname=Flint, birthday=1966-06-06, sex=Sex(2, M, Male)," +
                             " addresses=[Address(id=0, primary=false, street=Sunset Strip, number=77, zipCode=77555, city=Sunny Village)], bankAccounts=[])",
                     p.toString());
    }

    @Test
    void addBankAccount() {
        Person p = createTestPerson();
        p.addBankAccount(firstBankAccount());
        assertEquals(1, p.getBankAccounts().size());
        assertEquals("Person(id=42, name=Tom, surname=Flint, birthday=1966-06-06, sex=Sex(2, M, Male)," +
                             " addresses=[]," +
                             " bankAccounts=[BankAccount(id=0, primary=true, accountHolderName=Tom Flint," +
                             " bankName=Garden Onion Bank, iban=123456789, bic=GOB123X)])",
                     p.toString());
        p.addBankAccount(secondBankAccount());
        assertEquals(2, p.getBankAccounts().size());
        assertEquals("Person(id=42, name=Tom, surname=Flint, birthday=1966-06-06, sex=Sex(2, M, Male)," +
                             " addresses=[]," +
                             " bankAccounts=[BankAccount(id=0, primary=true, accountHolderName=Tom Flint," +
                             " bankName=Garden Onion Bank, iban=123456789, bic=GOB123X)," +
                             " BankAccount(id=0, primary=false, accountHolderName=Flint familiy account," +
                             " bankName=Shallot Savings, iban=123654789, bic=SHA1SAV)])",
                     p.toString());
    }

    @Test
    void removeBankAccount() {
        Person p = createTestPerson();
        p.addBankAccount(firstBankAccount());
        p.addBankAccount(secondBankAccount());
        assertEquals(2, p.getBankAccounts().size());
        assertEquals("Person(id=42, name=Tom, surname=Flint, birthday=1966-06-06, sex=Sex(2, M, Male)," +
                             " addresses=[]," +
                             " bankAccounts=[BankAccount(id=0, primary=true, accountHolderName=Tom Flint," +
                             " bankName=Garden Onion Bank, iban=123456789, bic=GOB123X)," +
                             " BankAccount(id=0, primary=false, accountHolderName=Flint familiy account," +
                             " bankName=Shallot Savings, iban=123654789, bic=SHA1SAV)])",
                     p.toString());
        p.removeBankAccount(firstBankAccount());
        assertEquals(1, p.getBankAccounts().size());
        assertEquals("Person(id=42, name=Tom, surname=Flint, birthday=1966-06-06, sex=Sex(2, M, Male)," +
                             " addresses=[]," +
                             " bankAccounts=[BankAccount(id=0, primary=false, accountHolderName=Flint familiy account," +
                             " bankName=Shallot Savings, iban=123654789, bic=SHA1SAV)])",
                     p.toString());
    }

    @Test
    void addAddressesAndBankAccounts() {
        Person p = createTestPerson();

        assertEquals("Person(id=42, name=Tom, surname=Flint, birthday=1966-06-06," +
                             " sex=Sex(2, M, Male), addresses=[], bankAccounts=[])",
                     p.toString());

        p.addAddress(firstAddress());
        assertEquals(1, p.getAddresses().size());
        assertEquals(0, p.getBankAccounts().size());

        p.addBankAccount(firstBankAccount());
        assertEquals(1, p.getAddresses().size());
        assertEquals(1, p.getBankAccounts().size());

        assertEquals("Person(id=42, name=Tom, surname=Flint, birthday=1966-06-06, sex=Sex(2, M, Male)," +
                             " addresses=[Address(id=0, primary=true, street=Main Street, number=42," +
                             " zipCode=12345, city=Myhometown)]," +
                             " bankAccounts=[BankAccount(id=0, primary=true, accountHolderName=Tom Flint," +
                             " bankName=Garden Onion Bank, iban=123456789, bic=GOB123X)])",
                     p.toString());

        p.addBankAccount(secondBankAccount());
        assertEquals(1, p.getAddresses().size());
        assertEquals(2, p.getBankAccounts().size());

        p.addAddress(secondAddress());
        assertEquals(2, p.getAddresses().size());
        assertEquals(2, p.getBankAccounts().size());

        assertEquals("Person(id=42, name=Tom, surname=Flint, birthday=1966-06-06, sex=Sex(2, M, Male)," +
                             " addresses=" +
                             "[Address(id=0, primary=true, street=Main Street, number=42, zipCode=12345, city=Myhometown)," +
                             " Address(id=0, primary=false, street=Sunset Strip, number=77, zipCode=77555, city=Sunny Village)]," +
                             " bankAccounts=" +
                             "[BankAccount(id=0, primary=true, accountHolderName=Tom Flint," +
                             " bankName=Garden Onion Bank, iban=123456789, bic=GOB123X)," +
                             " BankAccount(id=0, primary=false, accountHolderName=Flint familiy account," +
                             " bankName=Shallot Savings, iban=123654789, bic=SHA1SAV)])",
                     p.toString());
    }

    @Test
    void testToString() {
        Person p = createTestPerson();
        assertEquals("Person(id=42, name=Tom, surname=Flint, birthday=1966-06-06, sex=Sex(2, M, Male), addresses=[], bankAccounts=[])", p.toString());
    }
}