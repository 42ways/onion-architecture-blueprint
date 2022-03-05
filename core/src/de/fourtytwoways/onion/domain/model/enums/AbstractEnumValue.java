package de.fourtytwoways.onion.domain.model.enums;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
public abstract class AbstractEnumValue implements EnumValue {
    final EnumType type;
    @Getter final int id;
    @Getter final String key;
    @Getter final String value;

    protected AbstractEnumValue(EnumType type, int id, String key, String value) {
        this.type = type;
        this.id = id;
        this.key = key;
        this.value = value;
    }

    public final String toString() {
        return type + " [" + id + ", " + key + ", " + value + "]";
    }
}
