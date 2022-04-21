module onion.demo.main {
    requires java.logging;
    requires java.sql;
    requires org.slf4j;
    // iban4j is not a java module, but we add all class path entries to our build path
    requires iban4j;

    requires onion.application.main;
    requires onion.core.main;
    requires onion.infrastructure.main;
}
