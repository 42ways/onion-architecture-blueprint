package de.fourtytwo.enums.values;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwo.enums.types.EnumType;

import java.util.List;
import java.util.Optional;

public interface EnumFactory {
    List<EnumValue> getAllEntries(EnumType enumType);

    Optional<EnumValue> getEntry(EnumType enumType, int enumId);

    Optional<EnumValue> getEntryByKey(EnumType enumType, String enumKey);

    Optional<EnumValue> getEntryByValue(EnumType enumType, String enumValue);
}
