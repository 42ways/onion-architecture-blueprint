package de.fourtytwoways.onion.application.repository;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.domain.model.enumeration.EnumType;
import de.fourtytwoways.onion.domain.model.enumeration.EnumValue;

import java.util.List;
import java.util.Optional;

public interface EnumRepository extends Repository {
    List<EnumValue> getAllEntries(EnumType enumType);

    Optional<EnumValue> getEntry(EnumType enumType, int enumId);

    Optional<EnumValue> getEntryByKey(EnumType enumType, String enumKey);

    Optional<EnumValue> getEntryByValue(EnumType enumType, String enumValue);
}
