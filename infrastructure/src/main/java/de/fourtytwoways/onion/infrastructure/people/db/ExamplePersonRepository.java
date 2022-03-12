package de.fourtytwoways.onion.infrastructure.people.db;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.application.repositories.EnumRepository;
import de.fourtytwoways.onion.application.repositories.PersonRepository;
import de.fourtytwoways.onion.domain.entities.person.Address;
import de.fourtytwoways.onion.domain.entities.person.BankAccount;
import de.fourtytwoways.onion.domain.entities.person.Person;
import de.fourtytwoways.onion.domain.values.enumeration.EnumType;
import de.fourtytwoways.onion.domain.values.enumeration.Sex;
import de.fourtytwoways.onion.infrastructure.database.SessionFactory;
import org.hibernate.Session;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.function.BiConsumer;

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

            // Since we have to do LAZY loading for Bank Accounts, we have to do the mapping
            // to the domain model while still having an open session
            List<Person> people = results.stream().map(this::toPerson).toList();

            session.close();

            return people;
        }
    }

    private Person toPerson(PersonDAO personDAO) {
        if (personDAO == null)
            return null;
        Sex sex = (Sex) enumRepository.getEntryByKey(EnumType.SEX, personDAO.sex).orElse(null);
        Person person = new Person(personDAO.id, personDAO.name, personDAO.surname, personDAO.birthday, sex);
        for (AddressDAO addressDAO : personDAO.addressDAOS) {
            person.addAddress(new Address(addressDAO.id, addressDAO.isPrimary,
                                          addressDAO.street, addressDAO.number,
                                          addressDAO.zipCode, addressDAO.city));
        }
        for (BankAccountDAO bankAccountDAO : personDAO.bankAccountDAOS) {
            person.addBankAccount(new BankAccount(bankAccountDAO.id, bankAccountDAO.isPrimary,
                                                  bankAccountDAO.accountHolderName, bankAccountDAO.bankName,
                                                  bankAccountDAO.iban, bankAccountDAO.bic));
        }
        return person;
    }

    @Override
    public boolean savePerson(Person person) {
        return doPersonTransaction(person, Session::saveOrUpdate);
    }

    @Override
    public boolean deletePerson(Person person) {
        return doPersonTransaction(person, Session::delete);
    }

    private boolean doPersonTransaction(Person person, BiConsumer<Session, PersonDAO> sessionOperation) {
        // TODO: Error handling
        try (Session session = SessionFactory.getSession()) {
            session.beginTransaction();
            PersonDAO personDAO = new PersonDAO(person.getId(), person.getName(), person.getSurname(),
                                                person.getBirthday(), person.getSex().getKey(),
                                                person.getAddresses(), person.getBankAccounts());
            sessionOperation.accept(session, personDAO);
            session.getTransaction().commit();
            session.close();
            return true;
        }
    }
}
