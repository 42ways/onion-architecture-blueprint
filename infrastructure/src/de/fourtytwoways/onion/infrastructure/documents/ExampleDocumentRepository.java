package de.fourtytwoways.onion.infrastructure.documents;
// Copyright (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.application.repositories.DocumentRepository;
import de.fourtytwoways.onion.domain.entities.contract.Contract;
import de.fourtytwoways.onion.domain.entities.document.Document;
import de.fourtytwoways.onion.domain.entities.enumeration.DocumentType;
import de.fourtytwoways.onion.domain.entities.enumeration.EnumValue;

import java.util.Optional;

public class ExampleDocumentRepository implements DocumentRepository {
    @Override
    public void createDocument(Optional<EnumValue> type, Object contentObject) {
        if (type.isPresent()) {
            Contract contract = (Contract) contentObject;
            switch (type.get().getKey()) {
                case DocumentType.POLICY -> {
                    Document document = new ExamplePolicyDocument(contract);
                    document.print();
                }
                default -> throw new IllegalStateException("Unexpected value: " + type.get().getKey());
            }
        }
    }
}
