package de.fourtytwoways.onion.infrastructure.enums.provider;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.domain.values.enumeration.EnumType;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ENUM_VALUES")
public class EnumValueDAO {
    @Id
    int id;
    String key;
    String type;
    String value;

    EnumValueDAO() {
    }

    EnumValueDAO(int id, String key, EnumType type, String value) {
        this.id = id;
        this.key = key;
        this.type = type.name();
        this.value = value;
    }
}
