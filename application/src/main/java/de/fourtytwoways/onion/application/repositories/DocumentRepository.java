package de.fourtytwoways.onion.application.repositories;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.domain.entities.document.Document;
import de.fourtytwoways.onion.domain.values.enumeration.DocumentType;
import de.fourtytwoways.onion.domain.values.enumeration.EnumValue;

@SuppressWarnings("SameReturnValue")
public interface DocumentRepository extends Repository {
    default DocumentType getFirstPageType() {
        return DocumentType.FIRST_PAGE;
    }
    default DocumentType getPolicyType() {
        return DocumentType.POLICY;
    }

    Document createDocument(EnumValue type, Object contentObject);
}
