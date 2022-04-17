module onion.core.main {
    requires static lombok;
    requires static com.github.spotbugs.annotations;
    requires com.google.common;

    exports de.fourtytwoways.onion.domain.model.contract;
    exports de.fourtytwoways.onion.domain.model.document;
    exports de.fourtytwoways.onion.domain.event;
    exports de.fourtytwoways.onion.domain.model.person;
    exports de.fourtytwoways.onion.domain.usecase.contract;
    exports de.fourtytwoways.onion.domain.model.enumeration;
    exports de.fourtytwoways.onion.domain.model.asset;
}