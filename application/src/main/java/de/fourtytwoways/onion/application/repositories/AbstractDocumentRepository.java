package de.fourtytwoways.onion.application.repositories;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.domain.entities.document.Document;
import de.fourtytwoways.onion.domain.values.enumeration.DocumentType;

import java.util.Collection;

public abstract class AbstractDocumentRepository implements DocumentRepository {
    public Collection<Document> createDocuments(Collection<DocumentType> types, Object contentObject) {
        return types.stream().map(type -> createDocument(type, contentObject)).toList();
    }

}
