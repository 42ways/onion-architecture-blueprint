package de.fourtytwoways.people.db;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.database.SessionFactory;
import de.fourtytwoways.enums.provider.EnumValueDAO;
import de.fourtytwoways.enums.types.EnumType;
import de.fourtytwoways.enums.types.Sex;
import de.fourtytwoways.enums.values.EnumRepository;
import de.fourtytwoways.people.Person;
import de.fourtytwoways.people.PersonRepository;
import org.hibernate.Session;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

public class ExamplePersonRepository implements PersonRepository {
    private final EnumRepository enumRepository;

    public ExamplePersonRepository(EnumRepository enumRepository) {
        this.enumRepository = enumRepository;
    }

    @Override
    public Person getPersonById(int id) {
        try (Session session = SessionFactory.getSession()) {
            return toPerson(session.find(PersonDAO.class, new Integer(id)));
        }
    }

    @Override
    public List<Person> getPeopleByName(String name, String surname) {
        try (Session session = SessionFactory.getSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<PersonDAO> cr = cb.createQuery(PersonDAO.class);
            Root<PersonDAO> root = cr.from(PersonDAO.class);
            if ( name != null ) {
                cr.select(root).where(cb.equal(root.get("name"), name));
            }
            if ( surname != null ) {
                cr.select(root).where(cb.equal(root.get("surname"), surname));
            }
            Query query = session.createQuery(cr);

            List<PersonDAO> results = query.getResultList();
            session.close();

            return results.stream().map(personDAO -> toPerson(personDAO)).toList();
        }
    }

    private Person toPerson(PersonDAO personDAO) {
        if ( personDAO == null )
            return null;
        Sex sex = (Sex)enumRepository.getEntryByKey(EnumType.SEX, personDAO.sex).orElse(null);
        return new Person(personDAO.id, personDAO.name, personDAO.surname, personDAO.birthday, sex);
    }

    @Override
    public boolean savePerson(Person person) {
        // TODO: Error handling
        try (Session session = SessionFactory.getSession()) {
            session.beginTransaction();
            PersonDAO personDAO = new PersonDAO(person.getId(), person.getName(), person.getSurname(),
                    person.getBirthday(), person.getSex().getKey());
            session.persist(personDAO);
            session.getTransaction().commit();
            session.close();
            return true;
        }
    }
}
