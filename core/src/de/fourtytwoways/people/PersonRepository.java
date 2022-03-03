package de.fourtytwoways.people;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import java.util.List;
import java.util.Optional;

public interface PersonRepository {
    List<Person> getAllPeople();

    Optional<Person> getPersonByName(String name, String surname);

    boolean savePerson(Person person);
}
