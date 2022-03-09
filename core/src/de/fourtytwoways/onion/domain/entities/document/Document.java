package de.fourtytwoways.onion.domain.entities.document;
// Copyright (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.domain.entities.enumeration.DocumentType;

public interface Document {
    int getId();
    DocumentType getDocumentType();
    void print();
}