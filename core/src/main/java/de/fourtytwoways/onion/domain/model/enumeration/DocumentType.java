package de.fourtytwoways.onion.domain.model.enumeration;
// (c) 2022 Thomas Herrmann, 42ways GmbH

public class DocumentType extends AbstractEnumValue {
    // TODO: Is this old style of defining fixed application specific enums acceptable?
    //       Is there a better / more elegant way to do this?
    public static final DocumentType POLICY = new DocumentType(1, "POLICY", "Policy");
    public static final DocumentType FIRST_PAGE = new DocumentType(2, "FIRST_PAGE", "First Page");

    private DocumentType(int id, String key, String value) {
        super(EnumType.DOCUMENT_TYPE, id, key, value);
    }
}
