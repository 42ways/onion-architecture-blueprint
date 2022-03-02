package de.fourtytwo.enums.core;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwo.enums.types.EnumType;

import java.util.List;
import java.util.Optional;

public interface EnumFactory {
    List<EnumEntry> getAllEntries(EnumType enumType);

    Optional<EnumEntry> getEntry(EnumType enumType, int enumId);

    Optional<EnumEntry> getEntryByKey(EnumType enumType, String enumKey);

    Optional<EnumEntry> getEntryByValue(EnumType enumType, String enumValue);
}
