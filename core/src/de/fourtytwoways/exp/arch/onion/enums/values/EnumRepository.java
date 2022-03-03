package de.fourtytwoways.exp.arch.onion.enums.values;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.exp.arch.onion.enums.types.EnumType;

import java.util.List;
import java.util.Optional;

public interface EnumRepository {
    List<EnumValue> getAllEntries(EnumType enumType);

    Optional<EnumValue> getEntry(EnumType enumType, int enumId);

    Optional<EnumValue> getEntryByKey(EnumType enumType, String enumKey);

    Optional<EnumValue> getEntryByValue(EnumType enumType, String enumValue);
}