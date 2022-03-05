package de.fourtytwoways.onion.infrastructure.people.db;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.domain.entities.person.Address;
import de.fourtytwoways.onion.infrastructure.database.SessionFactory;
import de.fourtytwoways.onion.domain.entities.enumeration.EnumType;
import de.fourtytwoways.onion.domain.entities.enumeration.Sex;
import de.fourtytwoways.onion.application.repositories.EnumRepository;
import de.fourtytwoways.onion.domain.entities.person.Person;
import de.fourtytwoways.onion.application.repositories.PersonRepository;
import org.hibernate.Session;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class ExamplePersonRepository implements PersonRepository {
    private final EnumRepository enumRepository;

    public ExamplePersonRepository(EnumRepository enumRepository) {
        // TODO: Is this a good strategy? Could query EnumRepository when we need it...
        this.enumRepository = enumRepository;
    }

    @Override
    public Person getPersonById(int id) {
        try (Session session = SessionFactory.getSession()) {
            return toPerson(session.find(PersonDAO.class, id));
        }
    }

    @Override
    public List<Person> getPeopleByName(String name, String surname) {
        try (Session session = SessionFactory.getSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<PersonDAO> cr = cb.createQuery(PersonDAO.class);
            Root<PersonDAO> root = cr.from(PersonDAO.class);
            if (name != null) {
                cr.select(root).where(cb.equal(root.get("name"), name));
            }
            if (surname != null) {
                cr.select(root).where(cb.equal(root.get("surname"), surname));
            }
            Query query = session.createQuery(cr);

            List<PersonDAO> results = query.getResultList();
            session.close();

            return results.stream().map(this::toPerson).toList();
        }
    }

    private Person toPerson(PersonDAO personDAO) {
        if (personDAO == null)
            return null;
        Sex sex = (Sex) enumRepository.getEntryByKey(EnumType.SEX, personDAO.sex).orElse(null);
        Person person = new Person(personDAO.id, personDAO.name, personDAO.surname, personDAO.birthday, sex);
        for (AddressDAO addressDAO : personDAO.addressDAOS) {
            person.addAddress(new Address(addressDAO.id, addressDAO.isPrimary,
                    addressDAO.street, addressDAO.number, addressDAO.zipCode, addressDAO.city));
        }
        return person;
    }

    @Override
    public boolean savePerson(Person person) {
        // TODO: Error handling
        try (Session session = SessionFactory.getSession()) {
            session.beginTransaction();
            PersonDAO personDAO = new PersonDAO(person.getId(), person.getName(), person.getSurname(),
                    person.getBirthday(), person.getSex().getKey(), person.getAddresses());
            session.saveOrUpdate(personDAO);
            session.getTransaction().commit();
            session.close();
            return true;
        }
    }
}