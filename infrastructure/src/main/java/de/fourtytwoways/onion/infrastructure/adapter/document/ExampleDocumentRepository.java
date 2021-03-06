package de.fourtytwoways.onion.infrastructure.adapter.document;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.application.repository.AbstractDocumentRepository;
import de.fourtytwoways.onion.domain.model.contract.Contract;
import de.fourtytwoways.onion.domain.model.document.Document;
import de.fourtytwoways.onion.domain.model.enumeration.DocumentType;

public class ExampleDocumentRepository extends AbstractDocumentRepository {
    private static int lastId = 0;
    private static int nextId() {return ++lastId;}

    @Override
    public Document createDocument(DocumentType type, Object contentObject) {
        Contract contract = (Contract) contentObject;
        if (DocumentType.POLICY.equals(type)) {
            // TODO: better way to generate document id!
            Document policy = new ExamplePolicyDocument(nextId(), contract);
            policy.print();
            return policy;
        } else if (DocumentType.FIRST_PAGE.equals(type)) {
            // TODO: better way to generate document id!
            Document firstPage = new ExampleFirstPageDocument(nextId(), contract);
            firstPage.print();
            return firstPage;
        } else {
            throw new IllegalStateException("Unexpected value: " + type);
        }
    }
}
