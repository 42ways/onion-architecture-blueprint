package de.fourtytwoways.enums.provider;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.enums.types.EnumType;
import de.fourtytwoways.enums.types.Product;
import de.fourtytwoways.enums.types.Tariff;
import de.fourtytwoways.enums.values.EnumValue;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class ExampleEnumProviderTwo {

    private static final SessionFactory ourSessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    ExampleEnumProviderTwo() {
        insertExampleData();
    }

    private void insertExampleData() {
        try (Session session = getSession()) {
            session.beginTransaction();
            session.persist(new EnumTable( 1, "P870", EnumType.PRODUCT, "Gemischte Leben"));
            session.persist(new EnumTable( 2, "P890", EnumType.PRODUCT, "Was auch immer für ein Produkt"));
            session.persist(new EnumTable( 3, "T870", EnumType.TARIFF, "Gemischte Leben 870"));
            session.persist(new EnumTable( 4, "BU210", EnumType.TARIFF, "Berufsunfähigkeit Zusatzversicherung"));
            session.persist(new EnumTable( 5, "T890", EnumType.TARIFF, "Was auch immer für ein Tarif"));
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
        try (Session session = getSession()) {
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
