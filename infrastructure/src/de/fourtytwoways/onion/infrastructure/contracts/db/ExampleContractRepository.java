package de.fourtytwoways.onion.infrastructure.contracts.db;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.domain.entities.contract.Contract;
import de.fourtytwoways.onion.application.repositories.ContractRepository;
import de.fourtytwoways.onion.infrastructure.database.SessionFactory;
import de.fourtytwoways.onion.domain.entities.enumeration.Product;
import de.fourtytwoways.onion.application.repositories.EnumRepository;
import org.hibernate.Session;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class ExampleContractRepository implements ContractRepository {

    public ExampleContractRepository(EnumRepository enumRepository) {
        // TODO: Is this a good strategy? Could query EnumRepository in ContractDAO when we need it...
        ContractDAO.enumRepository = enumRepository;
    }

    @Override
    public Contract createContract(String contractNumber, Product product, LocalDate startDate, LocalDate endDate, BigDecimal benefit, BigDecimal premium) {
        return new ContractDAO(contractNumber, product, startDate, endDate, benefit, premium);
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
