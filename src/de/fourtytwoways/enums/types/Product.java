package de.fourtytwoways.enums.types;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.enums.core.AbstractEnumEntry;
import de.fourtytwoways.enums.core.EnumType;

public class Product extends AbstractEnumEntry {
    public Product(int id, String key, String value) {
        super(EnumType.PRODUCT, id, key, value);
    }
}
