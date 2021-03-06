package de.fourtytwoways.onion.infrastructure.adapter.document;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.application.repository.ContractRepository;
import de.fourtytwoways.onion.application.repository.DocumentRepository;
import de.fourtytwoways.onion.application.repository.RepositoryRegistry;
import de.fourtytwoways.onion.domain.model.contract.Contract;
import de.fourtytwoways.onion.domain.model.person.Person;
import de.fourtytwoways.onion.domain.model.asset.Money;
import de.fourtytwoways.onion.domain.model.enumeration.DocumentType;
import de.fourtytwoways.onion.domain.model.enumeration.Product;
import de.fourtytwoways.onion.domain.model.enumeration.Sex;
import de.fourtytwoways.onion.infrastructure.ExampleTestRepositoryRegistration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExampleDocumentRepositoryTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        ExampleTestRepositoryRegistration.registerRepos();

        System.setOut(new PrintStream(outputStreamCaptor, false, StandardCharsets.UTF_8));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    void createFirstPage() {
        Contract contract = ((ContractRepository)
                RepositoryRegistry.getInstance().getRepository(ContractRepository.class)).createContract(
                "0815",
                new Product(42, "TEST", "MyTestProduct"),
                Person.builder()
                        .id(1)
                        .name("Freddy")
                        .surname("Krüger")
                        .birthday(LocalDate.of(1987, 6, 5))
                        .sex(new Sex(1, "M", "Male"))
                        .build(),
                LocalDate.of(2022, 4, 1),
                LocalDate.of(2042, 3, 31),
                Money.valueOf(4711), Money.valueOf(19.71));

        DocumentRepository documentRepository = new ExampleDocumentRepository();
        ExampleFirstPageDocument firstPage =
                (ExampleFirstPageDocument) documentRepository.createDocument(DocumentType.FIRST_PAGE, contract);
        assertEquals(DocumentType.FIRST_PAGE, firstPage.getDocumentType());
        assertEquals("""
                             ---------- BEGIN DOCUMENT OUTPUT (FIRST PAGE)
                             Lieber Herr Freddy Krüger,
                             wir freuen uns, Ihnen im Anhang die Unterlagen Ihres  Versicherungsvertrages
                             0815 - MyTestProduct
                             übersenden zu können.
                             Bitte scheuen Sie sich nicht uns anzusprechen, sollten Sie irgendwelche Fragen haben.
                             Herzlichst,
                             Ihre Onion First Versicherungsgesellschaft auf Gegenseitigkeit
                             ---------- END DOCUMENT OUTPUT (FIRST PAGE)
                             """, outputStreamCaptor.toString(StandardCharsets.UTF_8));
        assert firstPage.isPrinted();
    }

    @Test
    void createPolicy() {
        Contract contract = ((ContractRepository)
                RepositoryRegistry.getInstance().getRepository(ContractRepository.class)).createContract(
                "0815",
                new Product(42, "TEST", "MyTestProduct"),
                Person.builder()
                        .id(1)
                        .name("Freddy")
                        .surname("Krüger")
                        .birthday(LocalDate.of(1987, 6, 5))
                        .sex(new Sex(1, "M", "Männlich"))
                        .build(),
                LocalDate.of(2022, 4, 1),
                LocalDate.of(2042, 3, 31),
                Money.valueOf(4711), Money.valueOf(19.71));

        DocumentRepository documentRepository = new ExampleDocumentRepository();
        ExamplePolicyDocument policy = (ExamplePolicyDocument) documentRepository.createDocument(DocumentType.POLICY, contract);
        assertEquals(DocumentType.POLICY, policy.getDocumentType());
        assertEquals("""
                             ---------- BEGIN DOCUMENT OUTPUT
                             VERSICHERUNGPOLICE 0815 über MyTestProduct
                             Die Gesamtleistung beträgt 4711.00 EUR
                             Der Beitrag beträgt 19.71 EUR
                             ---------- END DOCUMENT OUTPUT
                             """, outputStreamCaptor.toString(StandardCharsets.UTF_8));
        assert policy.isPrinted();
    }
}