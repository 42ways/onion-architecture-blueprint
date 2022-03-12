package de.fourtytwoways.onion.infrastructure.documents;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.domain.entities.contract.Contract;
import de.fourtytwoways.onion.domain.entities.document.Document;
import de.fourtytwoways.onion.domain.values.enumeration.DocumentType;

public class ExampleFirstPageDocument implements Document {
    final int id;
    final Contract contract;
    boolean printed = false;

    ExampleFirstPageDocument(int id, Contract contract) {
        this.id = id;
        this.contract = contract;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public DocumentType getDocumentType() {
        return DocumentType.FIRST_PAGE;
    }

    @Override
    public void print() {
        String policy = "Dear valued customer,\n" +
                "we are more than happy to send you attached the documents regarding your contract\n" +
                contract.getContractNumber() + " - " + contract.getProduct().getValue() + "\n" +
                "Please don't hesitate to contact us, if you have any questions\n" +
                "Sincerely,\nYour Onion First Insurance Company\n";
        System.out.println("---------- BEGIN DOCUMENT OUTPUT (FIRST PAGE)");
        System.out.print(policy);
        System.out.println("---------- END DOCUMENT OUTPUT (FIRST PAGE)");
        printed = true;
    }
}
