package de.fourtytwoways.onion.application.repositories;
// Copyright (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.domain.entities.enumeration.DocumentType;
import de.fourtytwoways.onion.domain.entities.enumeration.EnumValue;

public interface DocumentRepository extends Repository {
    default DocumentType getPolicyType() {
        return DocumentType.POLICY;
    }

    void createDocument(EnumValue type, Object contentObject);
}
