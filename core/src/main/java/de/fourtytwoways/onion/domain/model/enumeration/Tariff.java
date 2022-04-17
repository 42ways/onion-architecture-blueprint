package de.fourtytwoways.onion.domain.model.enumeration;
// (c) 2022 Thomas Herrmann, 42ways GmbH

public class Tariff extends AbstractEnumValue {
    public Tariff(int id, String key, String value) {
        super(EnumType.TARIFF, id, key, value);
    }
}
