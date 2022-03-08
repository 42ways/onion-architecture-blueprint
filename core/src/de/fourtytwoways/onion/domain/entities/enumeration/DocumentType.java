package de.fourtytwoways.onion.domain.entities.enumeration;
// Copyright (c) 2022 Thomas Herrmann, 42ways GmbH

public class DocumentType extends AbstractEnumValue {
    public static final String POLICY = "POLICY";

    public DocumentType(int id, String key, String value) {
        super(EnumType.DOCUMENT_TYPE, id, key, value);
    }
}
