package de.fourtytwoways.people;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import java.util.List;
import java.util.Optional;

public interface PersonRepository {
    Optional<Person> getPersonById(int id);

    List<Person> getPeopleByName(String name, String surname);

    boolean savePerson(Person person);
}
