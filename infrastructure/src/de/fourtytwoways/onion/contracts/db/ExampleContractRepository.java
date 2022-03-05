package de.fourtytwoways.onion.contracts.db;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.contracts.Contract;
import de.fourtytwoways.onion.contracts.ContractRepository;
import de.fourtytwoways.onion.database.SessionFactory;
import de.fourtytwoways.onion.enums.types.Product;
import de.fourtytwoways.onion.enums.values.EnumRepository;
import org.hibernate.Session;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.List;

public class ExampleContractRepository implements ContractRepository {

    public ExampleContractRepository(EnumRepository enumRepository) {
        // TODO: Is this a good strategy? Could query EnumRepository in ContractDAO when we need it...
        ContractDAO.enumRepository = enumRepository;
    }

    @Override
    public Contract createContract(String contractNumber, Product product, LocalDate beginDate, LocalDate endDate, double premium) {
        return new ContractDAO(contractNumber, product, beginDate, endDate, premium);
    }

    @Override
    public Contract getContractByNumber(String contractNumber) {
        try (Session session = SessionFactory.getSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<ContractDAO> cr = cb.createQuery(ContractDAO.class);
            Root<ContractDAO> root = cr.from(ContractDAO.class);
            cr.select(root).where(cb.equal(root.get("contractNumber"), contractNumber));
            Query query = session.createQuery(cr);

            List<ContractDAO> results = query.getResultList();
            session.close();

            return results.stream().findFirst().orElse(null);
        }
    }

    @Override
    public boolean saveContract(Contract contract) {
        // TODO: Error handling
        try (Session session = SessionFactory.getSession()) {
            session.beginTransaction();
            session.saveOrUpdate(contract);
            session.getTransaction().commit();
            session.close();
            return true;
        }
    }
}
