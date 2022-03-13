module onion.infrastructure.main {
    requires lombok;
    //noinspection Java9RedundantRequiresStatement
    requires java.naming;
    requires java.persistence;
    requires org.hibernate.orm.core;

    requires onion.application.main;
    requires onion.core.main;

    exports de.fourtytwoways.onion.infrastructure.contracts.db;
    exports de.fourtytwoways.onion.infrastructure.documents;
    exports de.fourtytwoways.onion.infrastructure.enums.provider;
    exports de.fourtytwoways.onion.infrastructure.people.db;
}