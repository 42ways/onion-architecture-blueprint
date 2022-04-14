package de.fourtytwoways.onion.infrastructure.adapter.documents;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.domain.entities.contract.Contract;
import de.fourtytwoways.onion.domain.values.enumeration.DocumentType;

public class ExamplePolicyDocument extends AbstractExampleDocument {

    ExamplePolicyDocument(int id, Contract contract) {
        super(id, contract);
    }

    @Override
    public DocumentType getDocumentType() {
        return DocumentType.POLICY;
    }

    @Override
    public void print() {
        String policy = "VERSICHERUNGPOLICE " + contract.getContractNumber() +
                " über " + contract.getProduct().getValue() + "\n" +
                "Die Gesamtleistung beträgt " +
                contract.getBenefit().amount() + " " + contract.getBenefit().currency() + "\n" +
                "Der Beitrag beträgt " +
                contract.getPremium().amount() + " " + contract.getPremium().currency() + "\n";
        System.out.println("---------- BEGIN DOCUMENT OUTPUT");
        System.out.print(policy);
        System.out.println("---------- END DOCUMENT OUTPUT");
        super.print();
    }
}
