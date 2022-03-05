package de.fourtytwoways.onion.application;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.domain.entities.person.Person;

import java.util.List;

public interface PersonRepository extends Repository {
    Person getPersonById(int id);

    List<Person> getPeopleByName(String name, String surname);

    boolean savePerson(Person person);
}
