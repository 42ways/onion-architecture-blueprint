package de.fourtytwoways.onion.application.repositories;
// Copyright (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.domain.entities.document.Document;
import de.fourtytwoways.onion.domain.entities.enumeration.DocumentType;
import de.fourtytwoways.onion.domain.entities.enumeration.EnumType;
import de.fourtytwoways.onion.domain.entities.enumeration.EnumValue;

import java.util.List;
import java.util.Optional;

public interface DocumentRepository extends Repository {
    default Optional<EnumValue> getPolicyType() {
        EnumRepository enumRepository = (EnumRepository) RepositoryRegistry.getInstance().getRepository(EnumRepository.class);
        return enumRepository.getEntryByKey(EnumType.DOCUMENT_TYPE, DocumentType.POLICY);
    }

    void createDocument(Optional<EnumValue> type, Object contentObject);
}
