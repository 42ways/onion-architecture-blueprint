package de.fourtytwoways.people;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import java.time.LocalDate;

public class Person {
    private int id;
    private String name;
    private String surname;
    private LocalDate birthday;
    private String sex;

    public Person(int id, String name, String surname, LocalDate birthday, String sex) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
        this.sex = sex;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public String getSex() {
        return sex;
    }
}
