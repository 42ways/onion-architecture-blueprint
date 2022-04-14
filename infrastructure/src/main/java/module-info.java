module onion.infrastructure.main {
    requires static lombok;
    //noinspection Java9RedundantRequiresStatement
    requires transitive java.naming;
    requires java.persistence;
    requires org.hibernate.orm.core;

    requires onion.application.main;
    requires onion.core.main;

    exports de.fourtytwoways.onion.infrastructure.database.contracts;
    exports de.fourtytwoways.onion.infrastructure.adapter.documents;
    exports de.fourtytwoways.onion.infrastructure.provider.enums;
    exports de.fourtytwoways.onion.infrastructure.database.people;
}