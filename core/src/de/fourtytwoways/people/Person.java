package de.fourtytwoways.people;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.enums.types.Sex;

import java.time.LocalDate;

public class Person {
    private int id;
    private String name;
    private String surname;
    private LocalDate birthday;
    private Sex sex;

    public Person(int id, String name, String surname, LocalDate birthday, Sex sex) {
        this.id = id;
        this.setName(name);
        this.setSurname(surname);
        this.setBirthday(birthday);
        this.setSex(sex);
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSurname() {
        return surname;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Sex getSex() {
        return sex;
    }

    public String toString() {
        return "PERSON [" + name + " " + surname + ", born on " + birthday + ", " + sex + "]";
    }
}
