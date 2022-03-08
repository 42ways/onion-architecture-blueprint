package de.fourtytwoways.onion.application.usecases.contract;
// Copyright (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.application.repositories.DocumentRepository;
import de.fourtytwoways.onion.domain.entities.contract.Contract;
import de.fourtytwoways.onion.domain.entities.document.Document;
import de.fourtytwoways.onion.domain.entities.enumeration.DocumentType;
import de.fourtytwoways.onion.domain.entities.enumeration.EnumValue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TestDocumentRepository implements DocumentRepository {
    private final List<Document> documents = new ArrayList<>();
    private final List<String> printOutput = new ArrayList<>();

    @Override
    public void createDocument(Optional<EnumValue> type, Object contentObject) {
        if (type.isPresent()) {
            Contract contract = (Contract) contentObject;
            switch (type.get().getKey()) {
                case DocumentType.POLICY -> documents.add(new Document() {
                    @Override
                    public void print() {
                        String policy = "POLICY for " + contract.getProduct().getValue() + "\n" +
                                "Benefit is " + contract.getBenefit() + "\n" +
                                "Premium is " + contract.getPremium() + "\n";
                        printOutput.add(policy);
                    }
                });
                default -> throw new IllegalStateException("Unexpected value: " + type.get().getKey());
            }
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
