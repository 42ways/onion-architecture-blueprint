package de.fourtytwoways.enums.provider;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.database.SessionFactory;
import de.fourtytwoways.enums.types.EnumType;
import de.fourtytwoways.enums.types.Product;
import de.fourtytwoways.enums.types.Tariff;
import de.fourtytwoways.enums.values.EnumValue;
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
        try (Session session = SessionFactory.getSession()) {
            session.beginTransaction();
            session.persist(new EnumTable( 1, "ARR", EnumType.PRODUCT, "Aufgeschobene Rürup-Rente"));
            session.persist(new EnumTable( 2, "RR", EnumType.TARIFF, "Rürup-Rente"));
            session.persist(new EnumTable( 3, "RHR", EnumType.TARIFF, "Rürup-Hinterbliebenenrente"));
            session.persist(new EnumTable( 4, "GV", EnumType.PRODUCT, "Gemischte Versicherung"));
            session.persist(new EnumTable( 5, "GV", EnumType.TARIFF, "Gemischte Versicherung"));
            session.persist(new EnumTable( 6, "GV2", EnumType.TARIFF, "Gemischte Versicherung auf 2 Leben"));
            session.persist(new EnumTable( 7, "AR", EnumType.PRODUCT, "Aufgeschobene Rente"));
            session.persist(new EnumTable( 8, "BUZR", EnumType.TARIFF, "BUZ Barrente"));
            session.persist(new EnumTable( 9, "BUZBB", EnumType.TARIFF, "BUZ Beitragsbefreiung"));
            session.persist(new EnumTable( 10, "ALR", EnumType.TARIFF, "Aufgeschobene Leibrente"));
            session.getTransaction().commit();
            session.close();
        }
    }

    List<EnumValue> getProducts() {
        List<EnumTable> results = getEnumValues(EnumType.PRODUCT);
        List<EnumValue> products = new ArrayList<>();
        for (EnumTable r : results) {
            products.add(new Product(r.id, r.key, r.value));
        }
        return products;
    }

    List<EnumValue> getTariffs() {
        List<EnumTable> results = getEnumValues(EnumType.TARIFF);
        List<EnumValue> tariffs = new ArrayList<>();
        for (EnumTable r : results) {
            tariffs.add(new Tariff(r.id, r.key, r.value));
        }
        return tariffs;
    }

    private List<EnumTable> getEnumValues(EnumType type) {
        try (Session session = SessionFactory.getSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<EnumTable> cr = cb.createQuery(EnumTable.class);
            Root<EnumTable> root = cr.from(EnumTable.class);
            cr.select(root).where(cb.equal(root.get("type"), type.name()));
            Query query = session.createQuery(cr);

            List<EnumTable> results = query.getResultList();
            session.close();

            return results;
        }
    }
}
