package de.fourtytwoways.onion.domain.entities.enumeration;
// (c) 2022 Thomas Herrmann, 42ways GmbH

public class Product extends AbstractEnumValue {
    public Product(int id, String key, String value) {
        super(EnumType.PRODUCT, id, key, value);
    }
}
