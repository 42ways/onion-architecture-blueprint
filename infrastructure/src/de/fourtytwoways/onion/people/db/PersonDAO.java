package de.fourtytwoways.onion.people.db;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "PEOPLE")
public class PersonDAO {
    @Id
    int id;
    String name;
    String surname;
    LocalDate birthday;
    String sex;

    PersonDAO() {
    }

    PersonDAO(int id, String name, String surname, LocalDate birthday, String sex) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
        this.sex = sex;
    }
}
