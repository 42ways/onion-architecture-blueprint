package de.fourtytwoways.onion;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.application.repositories.EnumRepository;
import de.fourtytwoways.onion.application.repositories.PersonRepository;
import de.fourtytwoways.onion.application.repositories.RepositoryRegistry;
import de.fourtytwoways.onion.domain.entities.person.Address;
import de.fourtytwoways.onion.infrastructure.enums.provider.ExampleEnumRepository;
import de.fourtytwoways.onion.domain.entities.enumeration.EnumType;
import de.fourtytwoways.onion.domain.entities.enumeration.Sex;
import de.fourtytwoways.onion.infrastructure.people.db.ExamplePersonRepository;
import de.fourtytwoways.onion.domain.entities.person.Person;

import java.time.LocalDate;
import java.util.List;

public class PersonDemo {

    public static void registerRepos() {
        EnumRepository enumRepository = new ExampleEnumRepository();
        PersonRepository personRepository = new ExamplePersonRepository(enumRepository);

        RepositoryRegistry.getInstance().
                registerRepository(EnumRepository.class, enumRepository).
                registerRepository(PersonRepository.class, personRepository);
    }

    public static void main(String[] args) {
        registerRepos();

        // TODO: Can we get rid of this cast?
        EnumRepository myEnumRepository = (EnumRepository) RepositoryRegistry.getInstance().getRepository(EnumRepository.class);
        PersonRepository myPersonRepository = (PersonRepository) RepositoryRegistry.getInstance().getRepository(PersonRepository.class);

        Sex male = (Sex)myEnumRepository.getEntryByKey(EnumType.SEX, "M").orElse(null);

        deleteDemoPeople();

        Person person1 = new Person(1, "Tom", "Cartoon", LocalDate.of(1966, 6, 6), male);
        myPersonRepository.savePerson(person1);

        Person person2 = new Person(2, "Jerry", "Sketch", LocalDate.of(1977, 7, 7), male);
        myPersonRepository.savePerson(person2);

        List<Person> people = myPersonRepository.getPeopleByName(null, null);

        for (Person person : people) {
            System.out.println(person);
        }

        Person p5 = myPersonRepository.getPersonById(5);
        System.out.println(p5);

        Person p1 = myPersonRepository.getPersonById(1);
        System.out.println(p1);

        p1.addAddress(new Address(true, "Rodeo Boulevard", "42", "88776", "Mytown"));
        System.out.println(p1);

        myPersonRepository.savePerson(p1);

        p1 = myPersonRepository.getPersonById(1);
        System.out.println(p1);

        person2.addAddress(new Address(true, "Main Street", "1", "87654", "High Noon Town"));
        myPersonRepository.savePerson(person2);

        p1.addAddress(new Address(false, "Oasisroad", "5", "76543", "Dessert Island"));
        System.out.println(p1);
        myPersonRepository.savePerson(p1);

        p1 = myPersonRepository.getPersonById(1);
        System.out.println(p1);

        Person p2 = myPersonRepository.getPersonById(2);
        System.out.println(p2);
    }

    private static void deleteDemoPeople() {
        PersonRepository myPersonRepository = (PersonRepository) RepositoryRegistry.getInstance().getRepository(PersonRepository.class);
        for (int i = 1; i <= 2; i++) {
            Person p = myPersonRepository.getPersonById(i);
            if ( p != null ) {
                myPersonRepository.deletePerson(p);
                System.out.println("Deleting person " + p);
            }
        }
    }
}
