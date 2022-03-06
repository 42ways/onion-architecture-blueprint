package de.fourtytwoways.onion.domain.entities.enumeration;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import lombok.Data;

@Data
public abstract class AbstractEnumValue implements EnumValue {
    final EnumType type;
    final int id;
    final String key;
    final String value;

    public final String toString() {
        return type + "(" + id + ", " + key + ", " + value + ")";
    }
}
