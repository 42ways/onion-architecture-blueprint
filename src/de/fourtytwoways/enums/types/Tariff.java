package de.fourtytwoways.enums.types;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.enums.core.AbstractEnumEntry;

public class Tariff extends AbstractEnumEntry {
    public Tariff(int id, String key, String value) {
        super(EnumType.TARIFF, id, key, value);
    }
}
