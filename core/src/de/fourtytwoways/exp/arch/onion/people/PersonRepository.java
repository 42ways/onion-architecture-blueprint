package de.fourtytwoways.exp.arch.onion.people;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import java.util.List;

public interface PersonRepository {
    Person getPersonById(int id);

    List<Person> getPeopleByName(String name, String surname);

    boolean savePerson(Person person);
}
