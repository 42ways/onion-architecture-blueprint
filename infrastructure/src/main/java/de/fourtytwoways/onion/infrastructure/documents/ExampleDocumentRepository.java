package de.fourtytwoways.onion.infrastructure.documents;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.application.repositories.DocumentRepository;
import de.fourtytwoways.onion.domain.entities.contract.Contract;
import de.fourtytwoways.onion.domain.entities.document.Document;
import de.fourtytwoways.onion.domain.values.enumeration.DocumentType;
import de.fourtytwoways.onion.domain.values.enumeration.EnumValue;

public class ExampleDocumentRepository implements DocumentRepository {
    private static int lastId = 0;

    @Override
    public void createDocument(EnumValue type, Object contentObject) {
        Contract contract = (Contract) contentObject;
        if (DocumentType.POLICY.equals(type)) {
            // TODO: better way to generate document id!
            Document document = new ExamplePolicyDocument(++lastId, contract);
            document.print();
        } else {
            throw new IllegalStateException("Unexpected value: " + type);
        }
    }
}
