package de.fourtytwoways.onion.infrastructure.people.db;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.application.repositories.PersonRepository;
import de.fourtytwoways.onion.domain.entities.person.Person;
import de.fourtytwoways.onion.infrastructure.database.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;

public final class ExamplePersonRepository implements PersonRepository {

    @Override
    public Person getPersonById(int id) {
        try (Session session = SessionFactory.getSession()) {
            return Optional.ofNullable(session.find(PersonDAO.class, id)).map(PersonDAO::toPerson).orElse(null);
        }
    }

    @Override
    public List<Person> getPeopleByName(String name, String surname) {
        try (Session session = SessionFactory.getSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<PersonDAO> cr = cb.createQuery(PersonDAO.class);
            Root<PersonDAO> root = cr.from(PersonDAO.class);
            List<Predicate> predicates = new ArrayList<>();
            if (name != null) {
                predicates.add(cb.equal(root.get("name"), name));
            }
            if (surname != null) {
                predicates.add(cb.equal(root.get("surname"), surname));
            }
            cr.select(root).where(predicates.toArray(predicates.toArray(new Predicate[0])));
            Query query = session.createQuery(cr);

            @SuppressWarnings("unchecked") List<PersonDAO> results = query.getResultList();

            // Since we have to do LAZY loading for Bank Accounts, we have to do the mapping
            // to the domain model while still having an open session
            List<Person> people = results.stream().map(PersonDAO::toPerson).toList();

            session.close();

            return people;
        }
    }

    @Override
    public Person savePerson(Person person) {
        doPersonTransaction(person, Session::saveOrUpdate);
        return getPersonById(person.getId());
    }

    @Override
    public void deletePerson(Person person) {
        doPersonTransaction(person, Session::delete);
    }

    private void doPersonTransaction(Person person, BiConsumer<Session, PersonDAO> sessionOperation) {
        try (Session session = SessionFactory.getSession()) {
            Transaction tx = session.beginTransaction();
            try {
                sessionOperation.accept(session, new PersonDAO(person));
            } catch (Exception e) {
                if (tx != null)
                    tx.rollback();
                throw e;
            }
            tx.commit();
        }
    }

}
