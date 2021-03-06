package de.fourtytwoways.onion.infrastructure.database.person;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.domain.model.person.Address;

import javax.persistence.*;

@Entity
@Table(name = "ADDRESSES")
public class AddressDAO {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    int id;
    @JoinColumn(name="person_id")
    @ManyToOne
    private PersonDAO personDAO;
    boolean isPrimary;
    String street;
    String number;
    String zipCode;
    String city;

    protected AddressDAO() {
    }

    AddressDAO(PersonDAO personDAO, Address address) {
        this.id = address.id();
        this.personDAO = personDAO;
        this.isPrimary = address.primary();
        this.street = address.street();
        this.number = address.number();
        this.zipCode = address.zipCode();
        this.city = address.city();
    }

    Address toAddress() {
        return Address.builder()
                .id(id)
                .primary(isPrimary)
                .street(street)
                .number(number)
                .zipCode(zipCode)
                .city(city)
                .build();
    }
}
