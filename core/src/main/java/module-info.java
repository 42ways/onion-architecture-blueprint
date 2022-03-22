module onion.core.main {
    requires static lombok;
    requires static com.github.spotbugs.annotations;
    requires com.google.common;
    requires kotlin.stdlib;

    exports de.fourtytwoways.onion.domain.entities.contract;
    exports de.fourtytwoways.onion.domain.entities.document;
    exports de.fourtytwoways.onion.domain.entities.event;
    exports de.fourtytwoways.onion.domain.entities.person;
    exports de.fourtytwoways.onion.domain.usecases.contract;
    exports de.fourtytwoways.onion.domain.values;
    exports de.fourtytwoways.onion.domain.values.enumeration;
}