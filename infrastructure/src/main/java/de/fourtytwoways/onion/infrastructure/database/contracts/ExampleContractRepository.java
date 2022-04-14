package de.fourtytwoways.onion.infrastructure.database.contracts;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.application.repositories.ContractRepository;
import de.fourtytwoways.onion.domain.entities.contract.Contract;
import de.fourtytwoways.onion.domain.entities.person.Person;
import de.fourtytwoways.onion.domain.values.Money;
import de.fourtytwoways.onion.domain.values.enumeration.Product;
import de.fourtytwoways.onion.infrastructure.database.session.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.List;

public class ExampleContractRepository implements ContractRepository {

    @Override
    public Contract createContract(String contractNumber, Product product, Person beneficiary, LocalDate startDate, LocalDate endDate, Money benefit, Money premium) {
        return new ContractDAO(contractNumber, product, beneficiary, startDate, endDate, benefit, premium);
    }

    @Override
    public Contract getContractByNumber(String contractNumber) {
        try (Session session = SessionFactory.getSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<ContractDAO> cr = cb.createQuery(ContractDAO.class);
            Root<ContractDAO> root = cr.from(ContractDAO.class);
            cr.select(root).where(cb.equal(root.get("contractNumber"), contractNumber));
            Query query = session.createQuery(cr);

            @SuppressWarnings("unchecked") List<ContractDAO> results = query.getResultList();
            session.close();

            return results.stream().findFirst().orElse(null);
        }
    }

    @Override
    public Contract saveContract(Contract contract) {
        try (Session session = SessionFactory.getSession()) {
            Transaction tx = session.beginTransaction();
            try {
                session.saveOrUpdate(contract);
            } catch (Exception e) {
                if (tx != null)
                    tx.rollback();
                throw e;
            }
            tx.commit();
        }
        return getContractByNumber(contract.getContractNumber());
    }
}
