package de.fourtytwoways.onion.enums.types;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.enums.values.AbstractEnumValue;

public class Tariff extends AbstractEnumValue {
    public Tariff(int id, String key, String value) {
        super(EnumType.TARIFF, id, key, value);
    }
}
