package de.fourtytwoways.onion.enums.values;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.Repository;
import de.fourtytwoways.onion.enums.types.EnumType;

import java.util.List;
import java.util.Optional;

public interface EnumRepository extends Repository {
    List<EnumValue> getAllEntries(EnumType enumType);

    Optional<EnumValue> getEntry(EnumType enumType, int enumId);

    Optional<EnumValue> getEntryByKey(EnumType enumType, String enumKey);

    Optional<EnumValue> getEntryByValue(EnumType enumType, String enumValue);
}
