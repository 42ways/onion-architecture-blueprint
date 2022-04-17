module onion.infrastructure.main {
    requires static lombok;
    //noinspection Java9RedundantRequiresStatement
    requires transitive java.naming;
    requires java.persistence;
    requires org.hibernate.orm.core;

    requires onion.application.main;
    requires onion.core.main;
    requires com.google.common;

    exports de.fourtytwoways.onion.infrastructure.database.contract;
    exports de.fourtytwoways.onion.infrastructure.adapter.document;
    exports de.fourtytwoways.onion.infrastructure.provider.enumeration;
    exports de.fourtytwoways.onion.infrastructure.database.person;
}