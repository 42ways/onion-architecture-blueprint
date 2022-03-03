package de.fourtytwoways.enums.types;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.enums.values.AbstractEnumValue;

public class Sex extends AbstractEnumValue {
    public Sex(int id, String key, String value) {
        super(EnumType.SEX, id, key, value);
    }
}
