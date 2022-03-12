package de.fourtytwoways.onion.application.usecases.contract;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.application.repositories.DocumentRepository;
import de.fourtytwoways.onion.domain.entities.contract.Contract;
import de.fourtytwoways.onion.domain.entities.document.Document;
import de.fourtytwoways.onion.domain.values.enumeration.DocumentType;
import de.fourtytwoways.onion.domain.values.enumeration.EnumValue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TestDocumentRepository implements DocumentRepository {
    private final Collection<Document> documents = new ArrayList<>();
    private final List<String> printOutput = new ArrayList<>();

    @Override
    public void createDocument(EnumValue type, Object contentObject) {
        Contract contract = (Contract) contentObject;
        if (DocumentType.POLICY.equals(type)) {
            documents.add(new Document() {
                @Override
                public int getId() {return 42;}

                @Override
                public DocumentType getDocumentType() {return DocumentType.POLICY;}

                @Override
                public void print() {
                    String policy = "POLICY for " + contract.getProduct().getValue() + "\n" +
                            "Benefit is " + contract.getBenefit() + "\n" +
                            "Premium is " + contract.getPremium() + "\n";
                    printOutput.add(policy);
                }
            });
        } else if (DocumentType.FIRST_PAGE.equals(type)) {
            documents.add(new Document() {
                @Override
                public int getId() {return 1;}

                @Override
                public DocumentType getDocumentType() {return DocumentType.FIRST_PAGE;}

                @Override
                public void print() {
                    String first_page = "Dear customer,\n" +
                            "we are happy to send you the policy for your new" +
                            " contract of our first class "
                            + contract.getProduct().getValue() + " as attachment\n";
                    printOutput.add(first_page);
                }
            });

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
        return printOutput;
    }
}
