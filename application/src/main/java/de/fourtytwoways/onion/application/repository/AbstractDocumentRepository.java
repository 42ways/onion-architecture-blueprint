package de.fourtytwoways.onion.application.repository;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.domain.model.document.Document;
import de.fourtytwoways.onion.domain.model.enumeration.DocumentType;

import java.util.Collection;

public abstract class AbstractDocumentRepository implements DocumentRepository {
    public Collection<Document> createDocuments(Collection<DocumentType> types, Object contentObject) {
        return types.stream().map(type -> createDocument(type, contentObject)).toList();
    }

}
