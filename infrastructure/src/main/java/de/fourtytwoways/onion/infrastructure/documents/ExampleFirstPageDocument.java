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
        String policy = "Lieber Kunde,\n" +
                "wir freuen uns, Ihnen im Anhang die Unterlagen Ihres " +
                " Versicherungsvertrages\n" +
                contract.getContractNumber() + " - " + contract.getProduct().getValue() + "\n" +
                " übersenden zu können.\n" +
                "Bitte scheuen Sie sich nicht uns anzusprechen, sollten Sie irgendwelche Fragen haben.\n" +
                "Herzlichst,\nIhre Onion First Versicherungsgesellschaft auf Gegenseitigkeit\n";
        System.out.println("---------- BEGIN DOCUMENT OUTPUT (FIRST PAGE)");
        System.out.print(policy);
        System.out.println("---------- END DOCUMENT OUTPUT (FIRST PAGE)");
        printed = true;
    }
}
