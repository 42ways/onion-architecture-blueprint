package de.fourtytwoways.enums.core;

public abstract class EnumEntry {
    final EnumType type;
    final int id;
    final String key;
    final String value;

    public EnumEntry(EnumType type, int id, String key, String value) {
        this.type = type;
        this.id = id;
        this.key = key;
        this.value = value;
    }

    final int getId() {
        return id;
    }

    final String getKey() {
        return key;
    }

    final String getValue() {
        return value;
    }
}
