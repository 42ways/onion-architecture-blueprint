package de.fourtytwoways.onion.application.repositories;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.domain.entities.document.Document;
import de.fourtytwoways.onion.domain.values.enumeration.DocumentType;

import java.util.Collection;

@SuppressWarnings("SameReturnValue")
public interface DocumentRepository extends Repository {
    default DocumentType getFirstPageType() {
        return DocumentType.FIRST_PAGE;
    }
    default DocumentType getPolicyType() {
        return DocumentType.POLICY;
    }

    Document createDocument(DocumentType type, Object contentObject);
    @SuppressWarnings("UnusedReturnValue")
    Collection<Document> createDocuments(Collection<DocumentType>types, Object contentObject);
}
