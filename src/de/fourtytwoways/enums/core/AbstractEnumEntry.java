package de.fourtytwoways.enums.core;
// (c) 2022 Thomas Herrmann, 42ways GmbH

public abstract class AbstractEnumEntry implements EnumEntry {
    final EnumType type;
    final int id;
    final String key;
    final String value;

    protected AbstractEnumEntry(EnumType type, int id, String key, String value) {
        this.type = type;
        this.id = id;
        this.key = key;
        this.value = value;
    }

    public final int getId() {
        return id;
    }

    public final String getKey() {
        return key;
    }

    public final String getValue() {
        return value;
    }

    public final String toString() {
        return "[" + id + ", " + key + ", " + value + "]";
    }
}
