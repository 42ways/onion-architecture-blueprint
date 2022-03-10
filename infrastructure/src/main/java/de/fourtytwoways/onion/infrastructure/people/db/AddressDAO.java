package de.fourtytwoways.onion.infrastructure.people.db;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.domain.entities.person.Address;

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

    AddressDAO() {
    }

    AddressDAO(PersonDAO personDAO, Address address) {
        this.id = address.getId();
        this.personDAO = personDAO;
        this.isPrimary = address.isPrimary();
        this.street = address.getStreet();
        this.number = address.getNumber();
        this.zipCode = address.getZipCode();
        this.city = address.getCity();
    }
}
