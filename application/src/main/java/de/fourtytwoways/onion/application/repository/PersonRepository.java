package de.fourtytwoways.onion.application.repository;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.domain.model.person.Person;

import java.util.List;

public interface PersonRepository extends Repository {
    Person getPersonById(int id);

    List<Person> getPeopleByName(String name, String surname);

    Person savePerson(Person person);

    void deletePerson(Person person);
}
