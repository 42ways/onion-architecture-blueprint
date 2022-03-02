package de.fourtytwoways.enums.provider;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.enums.types.EnumType;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class EnumTable {
    @Id
    int id;
    String key;
    String type;
    String value;

    EnumTable() {
    }

    EnumTable(int id, String key, EnumType type, String value) {
        this.id = id;
        this.key = key;
        this.type = type.name();
        this.value = value;
    }
}
