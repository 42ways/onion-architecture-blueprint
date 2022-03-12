package de.fourtytwoways.onion.application.repositories;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.domain.entities.person.Person;

import java.util.List;

public interface PersonRepository extends Repository {
    Person getPersonById(int id);

    List<Person> getPeopleByName(String name, String surname);

    void savePerson(Person person);

    void deletePerson(Person person);
}
