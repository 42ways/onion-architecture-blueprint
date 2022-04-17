package de.fourtytwoways.onion.domain.model.enumeration;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import com.google.common.base.CaseFormat;
import lombok.Data;

@Data
public abstract class AbstractEnumValue implements EnumValue {
    final EnumType type;
    final int id;
    final String key;
    final String value;

    public final String toString() {
        return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, type.toString()) + "(" + id + ", " + key + ", " + value + ")";
    }
}
