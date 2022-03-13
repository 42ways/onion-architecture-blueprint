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
        Person p1 = createTestPerson();
        assertEquals(0, p1.getAddresses().size());

        Person p2 = p1.addAddress(firstAddress());
        assertEquals(1, p2.getAddresses().size());
        assertEquals("Person(id=42, name=Tom, surname=Flint, birthday=1966-06-06, sex=Sex(2, M, Male)," +
                             " addresses=[Address(id=0, primary=true, street=Main Street, number=42, zipCode=12345, city=Myhometown)], bankAccounts=[])",
                     p2.toString());

        Person p3 = p2.addAddress(secondAddress());
        assertEquals(2, p3.getAddresses().size());
        assertEquals("Person(id=42, name=Tom, surname=Flint, birthday=1966-06-06, sex=Sex(2, M, Male)," +
                             " addresses=[Address(id=0, primary=true, street=Main Street, number=42, zipCode=12345, city=Myhometown)," +
                             " Address(id=0, primary=false, street=Sunset Strip, number=77, zipCode=77555, city=Sunny Village)], bankAccounts=[])",
                     p3.toString());
    }

    @Test
    void removeAddress() {
        Person p1 = createTestPerson().addAddress(firstAddress()).addAddress(secondAddress());
        assertEquals(2, p1.getAddresses().size());
        assertEquals("Person(id=42, name=Tom, surname=Flint, birthday=1966-06-06, sex=Sex(2, M, Male)," +
                             " addresses=[Address(id=0, primary=true, street=Main Street, number=42, zipCode=12345, city=Myhometown)," +
                             " Address(id=0, primary=false, street=Sunset Strip, number=77, zipCode=77555, city=Sunny Village)], bankAccounts=[])",
                     p1.toString());

        Person p2 = p1.removeAddress(firstAddress());
        assertEquals(1, p2.getAddresses().size());
        assertEquals("Person(id=42, name=Tom, surname=Flint, birthday=1966-06-06, sex=Sex(2, M, Male)," +
                             " addresses=[Address(id=0, primary=false, street=Sunset Strip, number=77, zipCode=77555, city=Sunny Village)], bankAccounts=[])",
                     p2.toString());
    }

    @Test
    void addBankAccount() {
        Person p1 = createTestPerson().addBankAccount(firstBankAccount());
        assertEquals(1, p1.getBankAccounts().size());
        assertEquals("Person(id=42, name=Tom, surname=Flint, birthday=1966-06-06, sex=Sex(2, M, Male)," +
                             " addresses=[]," +
                             " bankAccounts=[BankAccount(id=0, primary=true, accountHolderName=Tom Flint," +
                             " bankName=Garden Onion Bank, iban=123456789, bic=GOB123X)])",
                     p1.toString());

        Person p2 = p1.addBankAccount(secondBankAccount());
        assertEquals(2, p2.getBankAccounts().size());
        assertEquals("Person(id=42, name=Tom, surname=Flint, birthday=1966-06-06, sex=Sex(2, M, Male)," +
                             " addresses=[]," +
                             " bankAccounts=[BankAccount(id=0, primary=true, accountHolderName=Tom Flint," +
                             " bankName=Garden Onion Bank, iban=123456789, bic=GOB123X)," +
                             " BankAccount(id=0, primary=false, accountHolderName=Flint familiy account," +
                             " bankName=Shallot Savings, iban=123654789, bic=SHA1SAV)])",
                     p2.toString());
    }

    @Test
    void removeBankAccount() {
        Person p1 = createTestPerson().addBankAccount(firstBankAccount()).addBankAccount(secondBankAccount());
        assertEquals(2, p1.getBankAccounts().size());
        assertEquals("Person(id=42, name=Tom, surname=Flint, birthday=1966-06-06, sex=Sex(2, M, Male)," +
                             " addresses=[]," +
                             " bankAccounts=[BankAccount(id=0, primary=true, accountHolderName=Tom Flint," +
                             " bankName=Garden Onion Bank, iban=123456789, bic=GOB123X)," +
                             " BankAccount(id=0, primary=false, accountHolderName=Flint familiy account," +
                             " bankName=Shallot Savings, iban=123654789, bic=SHA1SAV)])",
                     p1.toString());

        Person p2 = p1.removeBankAccount(firstBankAccount());
        assertEquals(1, p2.getBankAccounts().size());
        assertEquals("Person(id=42, name=Tom, surname=Flint, birthday=1966-06-06, sex=Sex(2, M, Male)," +
                             " addresses=[]," +
                             " bankAccounts=[BankAccount(id=0, primary=false, accountHolderName=Flint familiy account," +
                             " bankName=Shallot Savings, iban=123654789, bic=SHA1SAV)])",
                     p2.toString());
    }

    @Test
    void addAddressesAndBankAccounts() {
        Person p1 = createTestPerson();

        assertEquals("Person(id=42, name=Tom, surname=Flint, birthday=1966-06-06," +
                             " sex=Sex(2, M, Male), addresses=[], bankAccounts=[])",
                     p1.toString());

        Person p2 = p1.addAddress(firstAddress());
        assertEquals(1, p2.getAddresses().size());
        assertEquals(0, p2.getBankAccounts().size());

        Person p3 = p2.addBankAccount(firstBankAccount());
        assertEquals(1, p3.getAddresses().size());
        assertEquals(1, p3.getBankAccounts().size());

        assertEquals("Person(id=42, name=Tom, surname=Flint, birthday=1966-06-06, sex=Sex(2, M, Male)," +
                             " addresses=[Address(id=0, primary=true, street=Main Street, number=42," +
                             " zipCode=12345, city=Myhometown)]," +
                             " bankAccounts=[BankAccount(id=0, primary=true, accountHolderName=Tom Flint," +
                             " bankName=Garden Onion Bank, iban=123456789, bic=GOB123X)])",
                     p3.toString());

        Person p4 = p3.addBankAccount(secondBankAccount());
        assertEquals(1, p4.getAddresses().size());
        assertEquals(2, p4.getBankAccounts().size());

        Person p5 = p4.addAddress(secondAddress());
        assertEquals(2, p5.getAddresses().size());
        assertEquals(2, p5.getBankAccounts().size());

        assertEquals("Person(id=42, name=Tom, surname=Flint, birthday=1966-06-06, sex=Sex(2, M, Male)," +
                             " addresses=" +
                             "[Address(id=0, primary=true, street=Main Street, number=42, zipCode=12345, city=Myhometown)," +
                             " Address(id=0, primary=false, street=Sunset Strip, number=77, zipCode=77555, city=Sunny Village)]," +
                             " bankAccounts=" +
                             "[BankAccount(id=0, primary=true, accountHolderName=Tom Flint," +
                             " bankName=Garden Onion Bank, iban=123456789, bic=GOB123X)," +
                             " BankAccount(id=0, primary=false, accountHolderName=Flint familiy account," +
                             " bankName=Shallot Savings, iban=123654789, bic=SHA1SAV)])",
                     p5.toString());
    }

    @Test
    void testToString() {
        Person p1 = createTestPerson();
        assertEquals("Person(id=42, name=Tom, surname=Flint, birthday=1966-06-06, sex=Sex(2, M, Male), addresses=[], bankAccounts=[])", p1.toString());
    }
}