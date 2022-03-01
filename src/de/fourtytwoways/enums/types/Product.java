package de.fourtytwoways.enums.types;

import de.fourtytwoways.enums.core.EnumEntry;
import de.fourtytwoways.enums.core.EnumType;

public class Product extends EnumEntry {
    public Product(int id, String key, String value) {
        super(EnumType.PRODUCT, id, key, value);
    }
}
