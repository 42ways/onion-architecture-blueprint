package de.fourtytwoways.onion.infrastructure.people.db;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.domain.entities.person.Address;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
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
    List<AddressDAO> addressDAOS = new ArrayList<>();

    PersonDAO() {
    }

    PersonDAO(int id, String name, String surname, LocalDate birthday, String sex, ArrayList<Address> addresses) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
        this.sex = sex;
        for (Address address : addresses) {
            addressDAOS.add(new AddressDAO(this, address));
        }
    }
}
