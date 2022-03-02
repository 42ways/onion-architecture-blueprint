package de.fourtytwo.enums.types;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwo.enums.values.AbstractEnumValue;

public class Tariff extends AbstractEnumValue {
    public Tariff(int id, String key, String value) {
        super(EnumType.TARIFF, id, key, value);
    }
}
