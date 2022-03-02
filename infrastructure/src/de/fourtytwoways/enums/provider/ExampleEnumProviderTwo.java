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
            session.getTransaction().commit();
            session.close();
        }
    }

    List<EnumValue> getProducts() {
        List<EnumValue> products = new ArrayList<>();
        try (Session session = getSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<EnumTable> cr = cb.createQuery(EnumTable.class);
            Root<EnumTable> root = cr.from(EnumTable.class);
            cr.select(root).where(cb.equal(root.get("type"), EnumType.PRODUCT.name()));
            Query query = session.createQuery(cr);

            List<EnumTable> results = query.getResultList();
            session.close();

            for (EnumTable r : results) {
                products.add(new Product(r.id, r.key, r.value));
            }
        }
        return products;
    }

    List<EnumValue> getTariffs() {
        List<EnumValue> tariffs = new ArrayList<>();
        tariffs.add(new Tariff(1, "T870", "Gemischte Leben"));
        tariffs.add(new Tariff(2, "BU210", "Berufsunfähigkeit Zusatzversicherung"));
        tariffs.add(new Tariff(3, "T890", "Was auch immer für ein Tarif"));
        return tariffs;
    }
}
