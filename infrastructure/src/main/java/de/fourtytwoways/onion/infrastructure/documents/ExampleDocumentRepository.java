package de.fourtytwoways.onion.infrastructure.documents;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.application.repositories.AbstractDocumentRepository;
import de.fourtytwoways.onion.domain.entities.contract.Contract;
import de.fourtytwoways.onion.domain.entities.document.Document;
import de.fourtytwoways.onion.domain.values.enumeration.DocumentType;

public class ExampleDocumentRepository extends AbstractDocumentRepository {
    private static int lastId = 0;

    @Override
    public Document createDocument(DocumentType type, Object contentObject) {
        Contract contract = (Contract) contentObject;
        if (DocumentType.POLICY.equals(type)) {
            // TODO: better way to generate document id!
            Document policy = new ExamplePolicyDocument(++lastId, contract);
            policy.print();
            return policy;
        } else if (DocumentType.FIRST_PAGE.equals(type)) {
            // TODO: better way to generate document id!
            Document firstPage = new ExampleFirstPageDocument(++lastId, contract);
            firstPage.print();
            return firstPage;
        } else {
            throw new IllegalStateException("Unexpected value: " + type);
        }
    }
}
