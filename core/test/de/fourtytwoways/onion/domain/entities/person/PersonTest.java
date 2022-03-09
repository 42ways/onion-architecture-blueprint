package de.fourtytwoways.onion.domain.entities.person;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.domain.entities.enumeration.Sex;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void addAddress() {
        Person p = createTestPerson();
        assertEquals(0, p.getAddresses().size());
        p.addAddress(firstAddress());
        assertEquals(1, p.getAddresses().size());
        assertEquals("Person(id=42, name=Tom, surname=Flint, birthday=1966-06-06, sex=Sex(2, M, Male)," +
                " addresses=[Address(id=0, primary=true, street=Main Street, number=42, zipCode=12345, city=Myhometown)])",
                p.toString());
        p.addAddress(secondAddress());
        assertEquals(2, p.getAddresses().size());
        assertEquals("Person(id=42, name=Tom, surname=Flint, birthday=1966-06-06, sex=Sex(2, M, Male)," +
                " addresses=[Address(id=0, primary=true, street=Main Street, number=42, zipCode=12345, city=Myhometown)," +
                " Address(id=0, primary=false, street=Sunset Strip, number=77, zipCode=77555, city=Sunny Village)])",
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
                " Address(id=0, primary=false, street=Sunset Strip, number=77, zipCode=77555, city=Sunny Village)])",
                p.toString());
        p.removeAddress(firstAddress());
        assertEquals(1, p.getAddresses().size());
        assertEquals("Person(id=42, name=Tom, surname=Flint, birthday=1966-06-06, sex=Sex(2, M, Male)," +
                " addresses=[Address(id=0, primary=false, street=Sunset Strip, number=77, zipCode=77555, city=Sunny Village)])",
                p.toString());
    }

    @Test
    void testToString() {
        Person p = createTestPerson();
        assertEquals("Person(id=42, name=Tom, surname=Flint, birthday=1966-06-06, sex=Sex(2, M, Male), addresses=[])", p.toString());
    }
}