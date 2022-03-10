package de.fourtytwoways.onion.infrastructure.enums.provider;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.infrastructure.database.SessionFactory;
import de.fourtytwoways.onion.domain.values.enumeration.EnumType;
import de.fourtytwoways.onion.domain.values.enumeration.Product;
import de.fourtytwoways.onion.domain.values.enumeration.Tariff;
import de.fourtytwoways.onion.domain.values.enumeration.EnumValue;
import org.hibernate.Session;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class ExampleEnumProviderTwo {

    ExampleEnumProviderTwo() {
        insertExampleData();
    }

    private void insertExampleData() {
        // TODO: Error handling
        try (Session session = SessionFactory.getSession()) {
            session.beginTransaction();
            session.saveOrUpdate(new EnumValueDAO( 1, "ARR", EnumType.PRODUCT, "Aufgeschobene Rürup-Rente"));
            session.saveOrUpdate(new EnumValueDAO( 2, "RR", EnumType.TARIFF, "Rürup-Rente"));
            session.saveOrUpdate(new EnumValueDAO( 3, "RHR", EnumType.TARIFF, "Rürup-Hinterbliebenenrente"));
            session.saveOrUpdate(new EnumValueDAO( 4, "GV", EnumType.PRODUCT, "Gemischte Versicherung"));
            session.saveOrUpdate(new EnumValueDAO( 5, "GV", EnumType.TARIFF, "Gemischte Versicherung"));
            session.saveOrUpdate(new EnumValueDAO( 6, "GV2", EnumType.TARIFF, "Gemischte Versicherung auf 2 Leben"));
            session.saveOrUpdate(new EnumValueDAO( 7, "AR", EnumType.PRODUCT, "Aufgeschobene Rente"));
            session.saveOrUpdate(new EnumValueDAO( 8, "BUZR", EnumType.TARIFF, "BUZ Barrente"));
            session.saveOrUpdate(new EnumValueDAO( 9, "BUZBB", EnumType.TARIFF, "BUZ Beitragsbefreiung"));
            session.saveOrUpdate(new EnumValueDAO( 10, "ALR", EnumType.TARIFF, "Aufgeschobene Leibrente"));
            session.getTransaction().commit();
            session.close();
        }
    }

    List<EnumValue> getProducts() {
        List<EnumValueDAO> results = getEnumValues(EnumType.PRODUCT);
        List<EnumValue> products = new ArrayList<>();
        for (EnumValueDAO r : results) {
            products.add(new Product(r.id, r.key, r.value));
        }
        return products;
    }

    List<EnumValue> getTariffs() {
        List<EnumValueDAO> results = getEnumValues(EnumType.TARIFF);
        List<EnumValue> tariffs = new ArrayList<>();
        for (EnumValueDAO r : results) {
            tariffs.add(new Tariff(r.id, r.key, r.value));
        }
        return tariffs;
    }

    private List<EnumValueDAO> getEnumValues(EnumType type) {
        try (Session session = SessionFactory.getSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<EnumValueDAO> cr = cb.createQuery(EnumValueDAO.class);
            Root<EnumValueDAO> root = cr.from(EnumValueDAO.class);
            cr.select(root).where(cb.equal(root.get("type"), type.name()));
            Query query = session.createQuery(cr);

            List<EnumValueDAO> results = query.getResultList();
            session.close();

            return results;
        }
    }
}
