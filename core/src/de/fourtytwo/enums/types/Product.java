package de.fourtytwo.enums.types;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwo.enums.values.AbstractEnumValue;

public class Product extends AbstractEnumValue {
    public Product(int id, String key, String value) {
        super(EnumType.PRODUCT, id, key, value);
    }
}
