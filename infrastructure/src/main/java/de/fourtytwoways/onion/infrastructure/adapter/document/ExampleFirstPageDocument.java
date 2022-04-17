package de.fourtytwoways.onion.infrastructure.adapter.document;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.domain.model.contract.Contract;
import de.fourtytwoways.onion.domain.model.enumeration.DocumentType;

public class ExampleFirstPageDocument extends AbstractExampleDocument {

    ExampleFirstPageDocument(int id, Contract contract) {
        super(id, contract);
    }

    @Override
    public DocumentType getDocumentType() {
        return DocumentType.FIRST_PAGE;
    }

    @Override
    public void print() {
        String policy = ("M".equals(contract.getBeneficiary().getSex().getKey()) ? "Lieber Herr " : "Liebe Frau ") +
                contract.getBeneficiary().getName() + " " + contract.getBeneficiary().getSurname() + ",\n" +
                "wir freuen uns, Ihnen im Anhang die Unterlagen Ihres " +
                " Versicherungsvertrages\n" +
                contract.getContractNumber() + " - " + contract.getProduct().getValue() + "\n" +
                "übersenden zu können.\n" +
                "Bitte scheuen Sie sich nicht uns anzusprechen, sollten Sie irgendwelche Fragen haben.\n" +
                "Herzlichst,\nIhre Onion First Versicherungsgesellschaft auf Gegenseitigkeit\n";
        System.out.println("---------- BEGIN DOCUMENT OUTPUT (FIRST PAGE)");
        System.out.print(policy);
        System.out.println("---------- END DOCUMENT OUTPUT (FIRST PAGE)");
        super.print();
    }
}
