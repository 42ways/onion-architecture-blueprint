package de.fourtytwoways.onion.infrastructure.documents;
// Copyright (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.domain.entities.contract.Contract;
import de.fourtytwoways.onion.domain.entities.document.Document;

public class ExamplePolicyDocument implements Document {
    Contract contract;
    boolean printed = false;

    ExamplePolicyDocument(Contract contract) {
        this.contract = contract;
    }

    @Override
    public void print() {
        String policy = "POLICY for " + contract.getProduct().getValue() + "\n" +
                "Benefit is " + contract.getBenefit() + "\n" +
                "Premium is " + contract.getPremium() + "\n";
        System.out.println("---------- BEGIN DOCUMENT OUTPUT");
        System.out.print(policy);
        System.out.println("---------- END DOCUMENT OUTPUT");
        printed = true;
    }
}
