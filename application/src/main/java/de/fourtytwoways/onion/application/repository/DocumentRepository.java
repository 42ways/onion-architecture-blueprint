package de.fourtytwoways.onion.application.repository;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.domain.model.document.Document;
import de.fourtytwoways.onion.domain.model.enumeration.DocumentType;

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
