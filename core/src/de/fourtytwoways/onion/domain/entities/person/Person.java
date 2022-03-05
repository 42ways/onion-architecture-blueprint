package de.fourtytwoways.onion.domain.entities.person;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.domain.entities.enumeration.Sex;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@EqualsAndHashCode
public class Person {
    @Getter private int id;
    @Getter @Setter private String name;
    @Getter @Setter private String surname;
    @Getter @Setter private LocalDate birthday;
    @Getter @Setter private Sex sex;

    public Person(int id, String name, String surname, LocalDate birthday, Sex sex) {
        this.id = id;
        this.setName(name);
        this.setSurname(surname);
        this.setBirthday(birthday);
        this.setSex(sex);
    }

    public String toString() {
        return "PERSON [" + name + " " + surname + ", born on " + birthday + ", " + sex + "]";
    }
}
