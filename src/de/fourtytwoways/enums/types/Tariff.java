package de.fourtytwoways.enums.types;

import de.fourtytwoways.enums.core.EnumEntry;
import de.fourtytwoways.enums.core.EnumType;

public class Tariff extends EnumEntry {
    public Tariff(int id, String key, String value) {
        super(EnumType.TARIFF, id, key, value);
    }
}
