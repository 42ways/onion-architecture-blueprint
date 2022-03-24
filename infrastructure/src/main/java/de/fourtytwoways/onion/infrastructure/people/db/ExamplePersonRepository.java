package de.fourtytwoways.onion.infrastructure.people.db;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.application.repositories.EnumRepository;
import de.fourtytwoways.onion.application.repositories.PersonRepository;
import de.fourtytwoways.onion.application.repositories.RepositoryRegistry;
import de.fourtytwoways.onion.domain.entities.person.Address;
import de.fourtytwoways.onion.domain.entities.person.BankAccount;
import de.fourtytwoways.onion.domain.entities.person.Person;
import de.fourtytwoways.onion.domain.values.enumeration.EnumType;
import de.fourtytwoways.onion.domain.values.enumeration.Sex;
import de.fourtytwoways.onion.infrastructure.database.SessionFactory;
import lombok.NonNull;
import org.hibernate.Session;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public final class ExamplePersonRepository implements PersonRepository {

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
            List<Person> people = results.stream().map(this::toPerson).toList();

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

    private EnumRepository enumRepository;

    private EnumRepository getEnumRepository() {
        if (enumRepository == null)
            enumRepository = (EnumRepository) RepositoryRegistry.getInstance().getRepository(EnumRepository.class);
        return enumRepository;
    }

    private void doPersonTransaction(Person person, BiConsumer<Session, PersonDAO> sessionOperation) {
        // TODO: Error handling
        try (Session session = SessionFactory.getSession()) {
            session.beginTransaction();
            sessionOperation.accept(session, new PersonDAO(person.getId(), person.getName(), person.getSurname(),
                                                               person.getBirthday(), person.getSex().getKey(),
                                                               person.getAddresses(), person.getBankAccounts()));
            session.getTransaction().commit();
            session.close();
        }
    }

    private Person toPerson(PersonDAO personDAO) {
        if (personDAO == null)
            return null;
        Sex sex = (Sex) this.getEnumRepository().getEntryByKey(EnumType.SEX, personDAO.sex).orElse(null);
        Person person = new Person(personDAO.id, personDAO.name, personDAO.surname, personDAO.birthday, sex);
        for (AddressDAO addressDAO : personDAO.addressDAOS) {
            person.addAddress(toAddress(addressDAO));
        }
        for (BankAccountDAO bankAccountDAO : personDAO.bankAccountDAOS) {
            person.addBankAccount(toBankAccount(bankAccountDAO));
        }
        return person;
    }

    private Address toAddress(@NonNull AddressDAO addressDAO) {
        return new Address(addressDAO.id, addressDAO.isPrimary,
                           addressDAO.street, addressDAO.number,
                           addressDAO.zipCode, addressDAO.city);
    }

    private BankAccount toBankAccount(@NonNull BankAccountDAO bankAccountDAO) {
        return new BankAccount(bankAccountDAO.id, bankAccountDAO.isPrimary,
                               bankAccountDAO.accountHolderName, bankAccountDAO.bankName,
                               bankAccountDAO.iban, bankAccountDAO.bic);
    }

}
