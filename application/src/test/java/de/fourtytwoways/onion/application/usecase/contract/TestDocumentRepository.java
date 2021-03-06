package de.fourtytwoways.onion.application.usecase.contract;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.application.repository.AbstractDocumentRepository;
import de.fourtytwoways.onion.domain.model.contract.Contract;
import de.fourtytwoways.onion.domain.model.document.Document;
import de.fourtytwoways.onion.domain.model.enumeration.DocumentType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class TestDocumentRepository extends AbstractDocumentRepository {
    private final Collection<Document> documents = new ArrayList<>();
    private final List<String> printOutput = new ArrayList<>();

    @Override
    public Document createDocument(DocumentType type, Object contentObject) {
        Contract contract = (Contract) contentObject;
        if (DocumentType.POLICY.equals(type)) {
            Document policy = new Document() {
                @Override
                public int getId() {return 42;}

                @Override
                public DocumentType getDocumentType() {return DocumentType.POLICY;}

                @Override
                public void print() {
                    String policy = "Versicherungspolice für das Produkt " + contract.getProduct().getValue() + "\n" +
                            "Die Leistung beträgt " +
                            contract.getBenefit().amount() + " " + contract.getBenefit().currency() + "\n" +
                            "Der Beitrag beträgt " +
                            contract.getPremium().amount() + " " + contract.getPremium().currency() + "\n";
                    printOutput.add(policy);
                }
            };
            documents.add(policy);
            return policy;
        } else if (DocumentType.FIRST_PAGE.equals(type)) {
            Document firstPage = new Document() {
                @Override
                public int getId() {return 1;}

                @Override
                public DocumentType getDocumentType() {return DocumentType.FIRST_PAGE;}

                @Override
                public void print() {
                    String first_page = "Lieber Kunde,\n" +
                            "wir freuen uns, Ihnen im Anhang die Unterlagen Ihres" +
                            " Versicherungsvertrages des Produkts "
                            + contract.getProduct().getValue() + " übersenden zu können.\n" +
                            "Herzlichst,\nIhre Onion First\n";
                    printOutput.add(first_page);
                }
            };
            documents.add(firstPage);
            return firstPage;
        } else {
            throw new IllegalStateException("Unexpected value: " + type);
        }
    }

    public void printAllDocuments() {
        for (Document document : documents) {
            document.print();
        }
    }

    public List<String> getPrintOutput() {
        return Collections.unmodifiableList(printOutput);
    }
}
