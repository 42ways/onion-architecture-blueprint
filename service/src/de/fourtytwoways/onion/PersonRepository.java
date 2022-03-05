package de.fourtytwoways.onion;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.Repository;
import de.fourtytwoways.onion.people.Person;

import java.util.List;

public interface PersonRepository extends Repository {
    Person getPersonById(int id);

    List<Person> getPeopleByName(String name, String surname);

    boolean savePerson(Person person);
}
