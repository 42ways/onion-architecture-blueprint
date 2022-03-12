package de.fourtytwoways.onion.infrastructure.documents;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.application.repositories.ContractRepository;
import de.fourtytwoways.onion.application.repositories.DocumentRepository;
import de.fourtytwoways.onion.application.repositories.RepositoryRegistry;
import de.fourtytwoways.onion.domain.entities.contract.Contract;
import de.fourtytwoways.onion.domain.values.Money;
import de.fourtytwoways.onion.domain.values.enumeration.DocumentType;
import de.fourtytwoways.onion.domain.values.enumeration.Product;
import de.fourtytwoways.onion.infrastructure.ExampleTestRepositoryRegistration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExampleDocumentRepositoryTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        ExampleTestRepositoryRegistration.registerRepos();

        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    void createDocument() {
        Contract contract = ((ContractRepository)
                RepositoryRegistry.getInstance().getRepository(ContractRepository.class)).createContract(
                "0815",
                new Product(42, "TEST", "MyTestProduct"),
                LocalDate.of(2022, 4, 1),
                LocalDate.of(2042, 3, 31),
                Money.valueOf(4711), Money.valueOf(19.71));
        DocumentRepository documentRepository = new ExampleDocumentRepository();
        documentRepository.createDocument(DocumentType.FIRST_PAGE, contract);
        assertEquals("---------- BEGIN DOCUMENT OUTPUT (FIRST PAGE)\n" +
                             "Lieber Kunde,\n" +
                             "wir freuen uns, Ihnen im Anhang die Unterlagen Ihres  Versicherungsvertrages\n" +
                             "0815 - MyTestProduct\n" +
                             " übersenden zu können.\n" +
                             "Bitte scheuen Sie sich nicht uns anzusprechen, sollten Sie irgendwelche Fragen haben.\n" +
                             "Herzlichst,\n" +
                             "Ihre Onion First Versicherungsgesellschaft auf Gegenseitigkeit\n" +
                             "---------- END DOCUMENT OUTPUT (FIRST PAGE)\n", outputStreamCaptor.toString());
    }
}