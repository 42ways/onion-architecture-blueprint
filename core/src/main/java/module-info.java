module onion.core.main {
    requires lombok;
    requires com.google.common;

    exports de.fourtytwoways.onion.domain.entities.contract;
    exports de.fourtytwoways.onion.domain.entities.document;
    exports de.fourtytwoways.onion.domain.entities.person;
    exports de.fourtytwoways.onion.domain.usecases.contract;
    exports de.fourtytwoways.onion.domain.values;
    exports de.fourtytwoways.onion.domain.values.enumeration;
}